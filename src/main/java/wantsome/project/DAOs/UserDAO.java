package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.UserDTO;

import java.sql.*;

public class UserDAO {

    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveUser(UserDTO user) {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, LOGIN_NAME, PASSWORD, EMAIL, PHONE, ADDRESS, USER_TYPE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLoginName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setInt(6, user.getPhone());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getUserType());
            preparedStatement.execute();
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
    }

    public UserDTO getUser(String username){
        UserDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM USERS WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, username);
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    result = new UserDTO(rs.getInt("ID"),
                            rs.getString("FIRST_NAME"),
                            rs.getString("LAST_NAME"),
                            rs.getString("LOGIN_NAME"),
                            rs.getString("PASSWORD"),
                            rs.getString("EMAIL"),
                            rs.getInt("PHONE"),
                            rs.getString("ADDRESS"),
                            rs.getString("USER_TYPE"));
                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkUserInDB (UserDTO user){
        boolean result = false;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM USERS WHERE LOGIN_NAME = ? AND EMAIL = ? AND PASSWORD = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, user.getLoginName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            try (ResultSet rs = preparedStatement.executeQuery()){
                if(rs.next()){
                    result = true;
                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkIfAdmin (UserDTO user){
        boolean result = false;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                   if(rs.getString("USER_TYPE").toLowerCase().equals("admin")){
                       result = true;
                    }
                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }

    public void deleteUser (UserDTO user){
        if (user.getFirstName() == null || user.getLastName() == null){
            throw new IllegalArgumentException("ser.getFirstName() OR user.getLastName() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "DELETE FROM USERS WHERE FIRST_NAME = ? and LAST_NAME = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());

            preparedStatement.execute();
            System.out.println("Deleted user: " + user);

        }
        catch (SQLException e ){
            e.printStackTrace();
        }
    }
}
