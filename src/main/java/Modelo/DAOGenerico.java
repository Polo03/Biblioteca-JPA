package Modelo;

import jakarta.persistence.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DAOGenerico<T> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Biblioteca");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Class<T> clase;

    public DAOGenerico(Class<T> clase) {
        this.clase = clase;
    }

    public T crearObjeto(Object... argumentos) {
        try {
            for (Constructor<?> constructor : clase.getDeclaredConstructors()) {
                if (constructor.getParameterCount() == argumentos.length) {
                    return clase.cast(constructor.newInstance(argumentos));
                }
            }
            throw new IllegalArgumentException("No se encontró un constructor con los argumentos proporcionados.");
        } catch (Exception e) {
            throw new RuntimeException("Error al crear instancia de " + clase.getSimpleName(), e);
        }
    }
    //ADD
    public void add(T entity){
        try {
            tx.begin();

            // Inserción manual con JPQL
            StringBuilder query = new StringBuilder("INSERT INTO ")
                    .append(clase.getSimpleName())
                    .append(" (");

            // Obtener los nombres de los campos de la entidad
            var fields = clase.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                query.append(fields[i].getName());
                if (i < fields.length - 1) query.append(", ");
            }

            query.append(") VALUES (");
            for (int i = 0; i < fields.length; i++) {
                query.append(":").append(fields[i].getName());
                if (i < fields.length - 1) query.append(", ");
            }
            query.append(")");

            // Crear la consulta
            var jpqlQuery = em.createQuery(query.toString());
            for (var field : fields) {
                field.setAccessible(true);
                jpqlQuery.setParameter(field.getName(), field.get(entity));
            }

            // Ejecutar la consulta
            jpqlQuery.executeUpdate();
            tx.commit();

            System.out.println("Entidad insertada manualmente: " + entity);
        } catch (Exception e) {
            tx.rollback();
            throw new RuntimeException("Error al agregar la entidad manualmente: " + entity, e);
        }
    }

    //SELECT BY ID
    public T getById(int id){
        return em.find(clase, id);
    }

    //SELECT ALL
    public ArrayList<T> getAll(){
        String nombreClase = clase.getSimpleName();
        return (ArrayList<T>) em.createQuery("SELECT e FROM "+nombreClase+" e", clase).getResultList();
    }

    //UPDATE
    public boolean update(T object){
        tx.begin();
        if(object != null)
            em.merge(object);
        else
            return false;
        tx.commit();
        return true;
    }

    //DELETE BY OBJECT
    public void deleteByObject(T object){
        tx.begin();
        em.remove(object);
        tx.commit();
    }

    //DELETE BY ID
    public boolean deleteById(int id){
        tx.begin();
        if(em.find(clase, id) != null)
            em.remove(em.find(clase, id));
        else
            return false;
        tx.commit();
        return true;
    }

    //DELETE ALL
    public int deleteAll(){
        return em.createQuery("DELETE FROM "+clase.getSimpleName()).executeUpdate();
    }




}
