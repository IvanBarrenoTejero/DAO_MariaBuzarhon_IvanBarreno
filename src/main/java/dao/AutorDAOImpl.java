package dao;

import model.Autor;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {


    @Override
    public void insertarAutor(Autor autor) throws SQLException {
        String SQL = "INSERT INTO Autor (nombre) VALUES (?)";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, autor.getNombre());
        preparedStatement.execute();
    }

    @Override
    public Autor buscarPorID(int id) throws SQLException {
        String SQL = "SELECT * FROM Autor WHERE id = ?";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()){
            return new Autor(resultSet.getInt("id"),resultSet.getString("nombre"));
        }
        return null;
    }

    @Override
    public List<Autor> listarAutores() throws SQLException {
        List<Autor> listaAutores = new ArrayList<>();
        String SQL = "SELECT * FROM Autor";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            listaAutores.add(new Autor(resultSet.getInt("id"),resultSet.getString("nombre")));
        }

        return listaAutores;
    }

    @Override
    public void actualizarAutor(Autor autor) throws SQLException {
        String SQL = "UPDATE Autor SET nombre = ? WHERE id = ?";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, autor.getNombre());
        preparedStatement.setInt(2, autor.getId());
        preparedStatement.execute();
    }

    @Override
    public void eliminarAutor(int id) throws SQLException {
        String SQL = "DELETE FROM Autor WHERE id = ?";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
