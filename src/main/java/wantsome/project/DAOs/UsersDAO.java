package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.UsersDTO;

import java.sql.*;

public class UsersDAO {

    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveUser(UsersDTO usersDTO) throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, LOGIN_NAME, PASSWORD, EMAIL, PHONE, ADDRESS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, usersDTO.getFirst_name());
        preparedStatement.setString(2, usersDTO.getLast_name());
        preparedStatement.setString(3, usersDTO.getLogin_name());
        preparedStatement.setString(4, usersDTO.getPassword());
        preparedStatement.setString(5, usersDTO.getEmail());
        preparedStatement.setInt(6, usersDTO.getPhone());
        preparedStatement.setString(7, usersDTO.getAddress());

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    public UsersDTO getUser(int id) throws SQLException {
        UsersDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "SELECT * FROM USERS WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            result = new UsersDTO(rs.getInt("ID"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"),
                    rs.getString("LOGIN_NAME"),
                    rs.getString("PASSWORD"),
                    rs.getString("EMAIL"),
                    rs.getInt("PHONE"),
                    rs.getString("ADDRESS"));
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return result;
    }

    public UsersDTO updateUser(UsersDTO usersDTO) throws SQLException {
      if (usersDTO.getUser_id() == null){
          throw new IllegalArgumentException("userDTO.getUser_id() is null");
      }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "UPDATE USERS SET FIRST_NAME = ?" +
                "LAST_NAME = ?" +
                "LOGIN_NAME = ?" +
                "PASSWORD = ?" +
                "EMAIL = ?" +
                "PHONE = ?" +
                "ADDRESS = ?" +
                "WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, usersDTO.getFirst_name());
        preparedStatement.setString(2, usersDTO.getLast_name());
        preparedStatement.setString(3, usersDTO.getLogin_name());
        preparedStatement.setString(4, usersDTO.getPassword());
        preparedStatement.setString(5, usersDTO.getEmail());
        preparedStatement.setInt(6, usersDTO.getPhone());
        preparedStatement.setString(7, usersDTO.getAddress());
        preparedStatement.setInt(8, usersDTO.getUser_id());
        preparedStatement.execute();

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return usersDTO;
    }

    public void deleteUser (UsersDTO usersDTO) throws SQLException {
        if (usersDTO.getUser_id() == null){
            throw new IllegalArgumentException("userDTO.getUser_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "DELETE FROM USERS WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, usersDTO.getUser_id());

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}
