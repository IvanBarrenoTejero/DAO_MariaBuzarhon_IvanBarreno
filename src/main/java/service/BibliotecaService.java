package service;

import dao.LibroDAO;
import dao.LibroAutorDAO;
import dao.AutorDAO;
import model.Libro;
import model.Autor;

import java.sql.SQLException;
import java.util.List;

public class BibliotecaService {
    private final LibroDAO libroDAO;
    private final LibroAutorDAO libroAutorDAO;
    private final AutorDAO autorDAO;

    public BibliotecaService(LibroDAO libroDAO, LibroAutorDAO libroAutorDAO, AutorDAO autorDAO) {
        this.libroDAO = libroDAO;
        this.libroAutorDAO = libroAutorDAO;
        this.autorDAO = autorDAO;
    }

    // ---------------------- LIBROS ----------------------
    public void addLibro(Libro libro) {
        try {
            libroDAO.addLibro(libro);
            System.out.println("Libro agregado correctamente: " + libro);
        } catch (Exception e) {
            System.err.println("Error al agregar libro: " + e.getMessage());
        }
    }

    public List<Libro> listarLibros() {
        try {
            return libroDAO.getAllLibros();
        } catch (Exception e) {
            System.err.println("Error al listar libros: " + e.getMessage());
            return List.of();
        }
    }

    public void cambiarTitulo(int id, String nuevoTitulo) {
        try {
            Libro libro = libroDAO.getLibroById(id);
            if (libro != null) {
                libro.setTitulo(nuevoTitulo);
                libroDAO.updateLibro(libro);
            } else {
                System.out.println("No se encontró el libro con id=" + id);
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar libro: " + e.getMessage());
        }
    }

    public void eliminarLibro(int id) {
        try {
            libroDAO.deleteLibro(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar libro: " + e.getMessage());
        }
    }

    // ---------------------- AUTORES ----------------------
    public void addAutor(Autor autor) {
        try {
            autorDAO.addAutor(autor);
            System.out.println("Autor agregado correctamente: " + autor);
        } catch (Exception e) {
            System.err.println("Error al agregar autor: " + e.getMessage());
        }
    }

    public List<Autor> listarAutores() {
        try {
            return autorDAO.getAllAutores();
        } catch (Exception e) {
            System.err.println("Error al listar autores: " + e.getMessage());
            return List.of();
        }
    }

    public void actualizarAutor(int id, String nuevoNombre) {
        try {
            Autor autor = autorDAO.getAutorById(id);
            if (autor != null) {
                autor.setNombre(nuevoNombre);
                autorDAO.updateAutor(autor);
                System.out.println("Autor actualizado correctamente: " + autor);
            } else {
                System.out.println("No se encontró el autor con id=" + id);
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar autor: " + e.getMessage());
        }
    }

    public void eliminarAutor(int id) {
        try {
            autorDAO.deleteAutor(id);
        } catch (Exception e) {
            System.err.println("Error al eliminar autor: " + e.getMessage());
        }
    }

    // ---------------------- RELACIÓN LIBRO-AUTOR ----------------------
    public void agregarAutorAlLibro(int libroId, int autorId) {
        try {
            libroAutorDAO.agregarAutorAlLibro(libroId, autorId);
        } catch (SQLException e) {
            System.err.println("Error al asociar autor al libro: " + e.getMessage());
        }
    }

    public void eliminarAutorDelLibro(int libroId, int autorId) {
        try {
            libroAutorDAO.eliminarAutorDelLibro(libroId, autorId);
        } catch (SQLException e) {
            System.err.println("Error al eliminar asociación libro-autor: " + e.getMessage());
        }
    }

    public List<Autor> obtenerAutoresPorLibro(int libroId) {
        try {
            return libroAutorDAO.obtenerAutoresPorLibro(libroId);
        } catch (SQLException e) {
            System.err.println("Error al obtener autores por libro: " + e.getMessage());
            return List.of();
        }
    }

    public List<Libro> obtenerLibrosPorAutor(int autorId) {
        try {
            return libroAutorDAO.obtenerLibrosPorAutor(autorId);
        } catch (SQLException e) {
            System.err.println("Error al obtener libros por autor: " + e.getMessage());
            return List.of();
        }
    }
}
