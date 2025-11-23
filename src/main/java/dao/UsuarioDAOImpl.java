package dao;

import model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    @Override
    public void addUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre) VALUES (?)";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.executeUpdate();

            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    usuario.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET nombre = ? WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, usuario.getNombre());
            ps.setInt(2, usuario.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteUsuario(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Usuario getUsuarioById(int id) throws SQLException {
        String sql = "SELECT id, nombre FROM Usuario WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Usuario(rs.getInt(1), rs.getString(2));
                }
            }
        }
        return null;
    }

    @Override
    public List<Usuario> getAllUsuarios() throws SQLException {
        String sql = "SELECT id, nombre FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return usuarios;
    }

}
