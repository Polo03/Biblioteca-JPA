package Modelo;

import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO extends DAOGenerico<Prestamo> {

    public PrestamoDAO() {
        super(Prestamo.class);
    }

    public List<Prestamo> getPrestamosByIdUsuario(int id) {
        return (ArrayList<Prestamo>) em.createQuery("SELECT p FROM Prestamo p Where p.usuario = "+id).getResultList();
    }
}
