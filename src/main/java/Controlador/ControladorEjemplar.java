package Controlador;

import Modelo.Ejemplar;
import Modelo.EjemplarDAO;

import java.util.List;

public class ControladorEjemplar {

    private EjemplarDAO dao;

    public ControladorEjemplar() {
        this.dao = new EjemplarDAO();
    }

    public void addEjemplar(Ejemplar e) {
        this.dao.add(e);
    }

    public List<Ejemplar> getAllEjemplares() {
        return this.dao.getAll();
    }

    public Ejemplar getEjemplarById(int id) {
        return this.dao.getById(id);
    }

    public Long getCountEjemplaresDisponiblesByISBN(String isbn){
        return this.dao.contarEjemplaresDisponiblesByISBN(isbn);
    }

    public void updateEjemplar(Ejemplar e) {
        this.dao.update(e);
    }

    public void deleteEjemplarById(int id) {
        this.dao.deleteById(id);
    }

    public void deleteAllEjemplares() {
        this.dao.deleteAll();
    }

}
