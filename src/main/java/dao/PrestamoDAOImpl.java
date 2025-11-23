package dao;

import model.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {
    @Override
    public void addPrestamo(Prestamo prestamo) throws SQLException {
        String sql = "INSERT INTO Prestamo (fechaInicio, fechaFin, usuarioId, libroId) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, prestamo.getFechaInicio());
            ps.setDate(2, prestamo.getFechaFin());
            ps.setInt(3, prestamo.getUsuarioId());
            ps.setInt(4, prestamo.getLibroId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    prestamo.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void updatePrestamo(Prestamo prestamo) throws SQLException {
        String sql = "UPDATE Prestamo SET fechaInicio = ?, fechaFIN = ?, usuarioId = ?, libroId = ? WHERE prestamoId = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, prestamo.getFechaInicio());
            ps.setDate(2, prestamo.getFechaFin());
            ps.setInt(3, prestamo.getUsuarioId());
            ps.setInt(4, prestamo.getLibroId());
            ps.setInt(5, prestamo.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void deletePrestamo(int id) throws SQLException {
        String sql =  "DELETE FROM Prestamo WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public Prestamo getPrestamoById(int id) throws SQLException {
        String sql = "SELECT id, fechaInicio, fechaFin, usuarioId, libroId FROM Prestamo WHERE id = ?";
        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setId(rs.getInt("id"));
                    prestamo.setFechaInicio(rs.getDate("fechaInicio"));
                    prestamo.setFechaFin(rs.getDate("fechaFin"));
                    prestamo.setUsuarioId(rs.getInt("usuarioId"));
                    prestamo.setLibroId(rs.getInt("libroId"));
                    return prestamo;
                }
                return null;
            }
        }
    }

    @Override
    public List<Prestamo> getAllPrestamos() throws SQLException {
        String sql = "SELECT id, fechaInicio, fechaFin, usuarioId, LibroId FROM Prestamo";
        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId(rs.getInt("id"));
                prestamo.setFechaInicio(rs.getDate("fechaInicio"));
                prestamo.setFechaFin(rs.getDate("fechaFin"));
                prestamo.setUsuarioId(rs.getInt("usuarioId"));
                prestamo.setLibroId(rs.getInt("libroId"));
                prestamos.add(prestamo);
            }
            return prestamos;
        }
    }

    @Override
    public List<Prestamo> getPrestamoByUsuario(int usuarioId) throws SQLException {
        String sql = "SELECT id, fechaInicio, fechaFin, usuarioId, libroId FROM Prestamo WHERE usuarioId = ?";
        List<Prestamo> prestamos = new ArrayList<>();

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, usuarioId);

            try (ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Prestamo prestamo = new Prestamo();
                    prestamo.setId(rs.getInt("id"));
                    prestamo.setFechaInicio(rs.getDate("fechaInicio"));
                    prestamo.setFechaFin(rs.getDate("fechaFin"));
                    prestamo.setUsuarioId(rs.getInt("usuarioId"));
                    prestamo.setLibroId(rs.getInt("libroId"));
                    prestamos.add(prestamo);
                }
            }
        }
        return prestamos;
    }

    // Usamos curdate para que coja solo las que tengan las fecha actual entre sus fechas final e incial
    @Override
    public List<Prestamo> getPrestamosActivos() throws SQLException {
        String sql = "SELECT id, fechaInicio, fechaFin, usuarioId, libroId FROM Prestamo WHERE CURDATE() BETWEEN fechaInicio AND fechaFin";
        ArrayList<Prestamo> prestamos = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setId(rs.getInt("id"));
                prestamo.setFechaInicio(rs.getDate("fechaInicio"));
                prestamo.setFechaFin(rs.getDate("fechaFin"));
                prestamo.setUsuarioId(rs.getInt("usuarioId"));
                prestamo.setLibroId(rs.getInt("libroId"));
                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }
}
