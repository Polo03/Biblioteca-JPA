package Modelo;

public class LibroDAO extends DAOGenerico<Libro>{

    public LibroDAO() {
        super(Libro.class);
    }

    public Libro getLibroByISBN(String isbn) {
        return em.createQuery("SELECT l FROM Libro l WHERE l.isbn="+isbn,Libro.class).getSingleResult();
    }

}
