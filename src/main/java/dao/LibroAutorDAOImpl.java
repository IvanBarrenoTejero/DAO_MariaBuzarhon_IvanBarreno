package dao;

import model.Autor;
import model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroAutorDAOImpl implements LibroAutorDAO {

    @Override
    public void agregarAutorAlLibro(int libroId, int autorId) throws SQLException {
        String sql = "INSERT INTO Libro_Autor (idLibro, idAutor) VALUES (?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, libroId);
            ps.setInt(2, autorId);
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminarAutorDelLibro(int libroId, int autorId) throws SQLException {
        String sql = "DELETE FROM Libro_Autor WHERE idLibro = ? AND idAutor = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, libroId);
            ps.setInt(2, autorId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Autor> obtenerAutoresPorLibro(int libroId) throws SQLException {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT a.id, a.nombre FROM Autor a " +
                "JOIN Libro_Autor la ON a.id = la.idAutor " +
                "WHERE la.idLibro = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, libroId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    autores.add(new Autor(rs.getInt("id"), rs.getString("nombre")));
                }
            }
        }
        return autores;
    }
    @Override
    public List<Libro> obtenerLibrosPorAutor(int autorId) throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT l.id, l.titulo, l.isbn FROM Libro l " +
                "JOIN Libro_Autor la ON l.id = la.idLibro " +
                "WHERE la.idAutor = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, autorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    libros.add(new Libro(rs.getInt("id"), rs.getString("titulo"), rs.getString("isbn")));
                }
            }
        }
        return libros;
    }
}
