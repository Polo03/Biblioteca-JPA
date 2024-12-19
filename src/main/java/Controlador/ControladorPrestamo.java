package Controlador;

import Modelo.Prestamo;
import Modelo.PrestamoDAO;

import java.util.List;

public class ControladorPrestamo {

    private PrestamoDAO dao;

    public ControladorPrestamo() {
        this.dao = new PrestamoDAO();
    }

    public void addPrestamo(Prestamo p) {
        this.dao.add(p);
    }

    public List<Prestamo> getAllPrestamos() {
        return this.dao.getAll();
    }

    public Prestamo getPrestamoById(int id) {
        return this.dao.getById(id);
    }

    public List<Prestamo> getPrestamoByIdUsuario(int idUsuario) {
        return this.dao.getPrestamosByIdUsuario(idUsuario);
    }

    public void updatePrestamo(Prestamo p) {
        this.dao.update(p);
    }

    public void deletePrestamoById(int id) {
        this.dao.deleteById(id);
    }

    public void deleteAllPrestamos() {
        this.dao.deleteAll();
    }

}
