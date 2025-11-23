package dao;

import model.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {

    private static final String INSERTAR_AUTOR = "INSERT INTO autor(nombre) VALUES (?)";
    private static final String BUSCAR_POR_ID = "SELECT * FROM autor WHERE id = ?";
    private static final String LISTAR_AUTORES = "SELECT * FROM autor";
    private static final String ACTUALIZAR_AUTOR = "UPDATE autor SET nombre = ? WHERE id = ?";
    private static final String ELIMINAR_AUTOR = "DELETE FROM autor WHERE id = ?";


    @Override
    public void addAutor(Autor autor) throws SQLException {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERTAR_AUTOR, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, autor.getNombre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    autor.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Autor getAutorById(int id) throws SQLException {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(BUSCAR_POR_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Autor(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        }
        return null;
    }

    @Override
    public List<Autor> getAllAutores() throws SQLException {
        List<Autor> autores = new ArrayList<>();
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(LISTAR_AUTORES);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                autores.add(new Autor(rs.getInt("id"), rs.getString("nombre")));
            }
        }
        return autores;
    }

    @Override
    public void updateAutor(Autor autor) throws SQLException {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(ACTUALIZAR_AUTOR)) {
            ps.setString(1, autor.getNombre());
            ps.setInt(2, autor.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deleteAutor(int id) throws SQLException {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(ELIMINAR_AUTOR)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
