package dao;

import model.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDAO {
    void addUsuario(Usuario usuario) throws SQLException;
    void updateUsuario(Usuario usuario)throws SQLException;
    void deleteUsuario(int id)throws SQLException;
    Usuario getUsuarioById(int id)throws SQLException;
    List<Usuario> getAllUsuarios()throws SQLException;
}
