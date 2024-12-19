package Modelo;

import jakarta.persistence.TypedQuery;

public class EjemplarDAO extends DAOGenerico<Ejemplar>{

    public EjemplarDAO() {
        super(Ejemplar.class);
    }

    public Long contarEjemplaresDisponiblesByISBN(String isbn){
        String jpql = "SELECT COUNT(e) FROM Ejemplar e WHERE e.isbn.isbn = :isbn AND e.estado = 'Disponible'";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("isbn", isbn); // Asegúrate de pasar un String aquí
        Long count = query.getSingleResult();
        return count;

    }
}
