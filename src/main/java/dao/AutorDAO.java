package dao;

import model.Autor;
import java.sql.SQLException;
import java.util.List;

public interface AutorDAO {

    void addAutor(Autor autor) throws SQLException;
    Autor getAutorById(int id) throws SQLException;
    List<Autor> getAllAutores() throws SQLException;
    void updateAutor(Autor autor) throws SQLException;
    void deleteAutor(int id) throws SQLException;

}
