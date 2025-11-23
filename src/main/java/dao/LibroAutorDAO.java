package dao;

import model.Libro;
import model.Autor;

import java.sql.SQLException;
import java.util.List;

public interface LibroAutorDAO {

    void agregarAutorAlLibro(int libroId, int autorId) throws SQLException;

    void eliminarAutorDelLibro(int libroId, int autorId) throws SQLException;

    List<Autor> obtenerAutoresPorLibro(int libroId) throws SQLException;

    List<Libro> obtenerLibrosPorAutor(int autorId) throws SQLException;
}
