package service;

import dao.PrestamoDAO;
import model.Prestamo;

import java.util.List;

public class PrestamoService {
    private PrestamoDAO prestamoDAO;

    public PrestamoService(PrestamoDAO prestamoDAO) {
        this.prestamoDAO = prestamoDAO;
    }

    public void addPrestamo(Prestamo prestamo) {
        try {
            prestamoDAO.addPrestamo(prestamo);
            System.out.println("Service: Préstamo añadido -> " + prestamo);
        } catch (Exception e) {
            System.err.println("Error al añadir préstamo: " + e.getMessage());
        }
    }

    public List<Prestamo> getAllPrestamos() {
        try {
            return prestamoDAO.getAllPrestamos();
        } catch (Exception e) {
            System.err.println("Error al obtener préstamos: " + e.getMessage());
            return List.of();
        }
    }

    public Prestamo getPrestamoById(int id) {
        try {
            return prestamoDAO.getPrestamoById(id);
        } catch (Exception e) {
            System.err.println("Error al obtener préstamo por id: " + e.getMessage());
            return null;
        }
    }

    public void updatePrestamo(Prestamo prestamo) {
        try {
            prestamoDAO.updatePrestamo(prestamo);
            System.out.println("Service: Préstamo actualizado -> " + prestamo);
        } catch (Exception e) {
            System.err.println("Error al actualizar préstamo: " + e.getMessage());
        }
    }

    public void deletePrestamo(int id) {
        try {
            prestamoDAO.deletePrestamo(id);
            System.out.println("Service: Préstamo eliminado (id=" + id + ")");
        } catch (Exception e) {
            System.err.println("Error al eliminar préstamo: " + e.getMessage());
        }
    }

    public List<Prestamo> getPrestamosByUsuario(int usuarioId) {
        try {
            return prestamoDAO.getPrestamoByUsuario(usuarioId);
        } catch (Exception e) {
            System.err.println("Error al obtener préstamos por usuario: " + e.getMessage());
            return List.of();
        }
    }

    public List<Prestamo> getPrestamosActivos() {
        try {
            return prestamoDAO.getPrestamosActivos();
        } catch (Exception e) {
            System.err.println("Error al obtener préstamos activos: " + e.getMessage());
            return List.of();
        }
    }
}