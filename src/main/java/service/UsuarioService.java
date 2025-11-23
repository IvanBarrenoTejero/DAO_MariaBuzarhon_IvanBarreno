package service;

import dao.UsuarioDAO;
import model.Usuario;

import java.util.List;

public class UsuarioService {
    private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void addUsuario(Usuario usuario) {
        try {
            usuarioDAO.addUsuario(usuario);
            System.out.println("Usuario añadido " + usuario);
        } catch (Exception e) {
            System.err.println("Error al añadir usuario: " + e.getMessage());
        }
    }

    public List<Usuario> getAllUsuarios() {
        try {
            return usuarioDAO.getAllUsuarios();
        } catch (Exception e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
            return List.of();
        }
    }

    public Usuario getUsuarioById(int id) {
        try {
            return usuarioDAO.getUsuarioById(id);
        } catch (Exception e) {
            System.err.println("Error al obtener usuario por id: " + e.getMessage());
            return null;
        }
    }

    public void updateUsuario(Usuario usuario) {
        try {
            usuarioDAO.updateUsuario(usuario);
            System.out.println("Usuario actualizado " + usuario);
        } catch (Exception e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    public void deleteUsuario(int id) {
        try {
            usuarioDAO.deleteUsuario(id);
            System.out.println("Usuario eliminado (id=" + id + ")");
        } catch (Exception e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
}