package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;

import java.util.List;

public class ControladorUsuario {

    private UsuarioDAO dao;

    public ControladorUsuario() {
        this.dao = new UsuarioDAO();
    }

    public void addUsuario(Usuario usuario) {
        this.dao.add(usuario);
    }

    public List<Usuario> getAllUsuarios() {
        return this.dao.getAll();
    }

    public Usuario getUsuarioById(int id) {
        return this.dao.getById(id);
    }

    public void updateUsuario(Usuario usuario) {
        this.dao.update(usuario);
    }

    public void deleteUsuarioById(int id) {
        this.dao.deleteById(id);
    }

    public void deleteAllUsuarios() {
        this.dao.deleteAll();
    }
}
