package dao;

import model.Prestamo;

import java.sql.SQLException;
import java.util.List;

public interface PrestamoDAO {
    void addPrestamo(Prestamo prestamo)throws SQLException;
    void updatePrestamo(Prestamo prestamo)throws SQLException;
    void deletePrestamo(int id)throws SQLException;
    Prestamo getPrestamoById(int id)throws SQLException;
    List<Prestamo> getAllPrestamos()throws SQLException;
    List<Prestamo> getPrestamoByUsuario(int usuarioId)throws SQLException;
    List<Prestamo> getPrestamosActivos()throws SQLException;
}
