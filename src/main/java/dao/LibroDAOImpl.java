package dao;

import model.Libro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {

    @Override
    public void addLibro(Libro libro) throws Exception {
        String sql = "INSERT INTO Libro (titulo, isbn) VALUES (?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) libro.setId(rs.getInt(1));
            }

            System.out.println("DAO: Libro insertado -> " + libro);
        }
    }

    @Override
    public List<Libro> getAllLibros() throws Exception {
        String sql = "SELECT id, titulo, isbn FROM Libro";
        List<Libro> lista = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("isbn")
                );
                lista.add(libro);
            }
        }

        return lista;
    }

    @Override
    public Libro getLibroById(int id) throws Exception {
        String sql = "SELECT id, titulo, isbn FROM Libro WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Libro(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("isbn")
                    );
                }
            }
        }

        return null;
    }

    @Override
    public void updateLibro(Libro libro) throws Exception {
        String sql = "UPDATE Libro SET titulo = ?, isbn = ? WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.setInt(3, libro.getId());

            ps.executeUpdate();

            System.out.println("DAO: Libro actualizado -> " + libro);
        }
    }

    @Override
    public void deleteLibro(int id) throws Exception {
        String sql = "DELETE FROM Libro WHERE id = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("DAO: Libro eliminado (ID=" + id + ")");
        }
    }
}
