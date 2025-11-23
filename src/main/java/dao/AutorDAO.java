package dao;

import model.Autor;

import java.sql.SQLException;
import java.util.List;

public interface AutorDAO {
    void insertarAutor(Autor autor) throws SQLException;
    Autor buscarPorID(int id) throws SQLException;
    List<Autor> listarAutores() throws SQLException;
    void actualizarAutor(Autor autor) throws SQLException;
    void eliminarAutor(int id) throws SQLException;
}
