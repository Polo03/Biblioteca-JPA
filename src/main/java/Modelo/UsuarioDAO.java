package Modelo;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DAOGenerico<Usuario>{

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public List<Usuario> dameUsuariosAdministradores() {
        String sql="select u from Usuario u where u.tipo = 'Administrador'";
        ArrayList<Usuario> usuarios= (ArrayList<Usuario>) em.createQuery(sql).getResultList();
        return usuarios;
    }
}
