package Controlador;

import Modelo.DAOGenerico;
import Modelo.Libro;
import Modelo.LibroDAO;

import java.util.List;

public class ControladorLibro {

    private LibroDAO dao;

    public ControladorLibro() {
        this.dao=new LibroDAO();
    }

    public void addLibro(Libro libro) {
        this.dao.add(libro);
    }

    public List<Libro> getAllLibros() {
        return this.dao.getAll();
    }

    public Libro getLibroById(int id) {
        return this.dao.getById(id);
    }

    public Libro getLibroByISBN(String isbn) {
        return this.dao.getLibroByISBN(isbn);
    }

    public void updateLibro(Libro libro) {
        this.dao.update(libro);
    }

    public void deleteLibro(int id) {
        this.dao.deleteById(id);
    }

    public void deleteAllLibros(){
        this.dao.deleteAll();
    }
}
