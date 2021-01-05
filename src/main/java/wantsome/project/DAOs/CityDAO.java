package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.CityDTO;
import wantsome.project.DTOs.UserDTO;

import java.sql.*;

public class CityDAO {

    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveCity (CityDTO city) throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "INSERT INTO CITIES (NAME) VALUES (?)";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, city.getName());
            preparedStatement.execute();
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
    }

    public CityDTO getCity (int id) {
        CityDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM CITIES WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    result = new CityDTO(rs.getInt("ID"), rs.getString("NAME"));
                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }

    public void deleteCity (CityDTO city) throws SQLException {
        if (city.getCityID() == null) {
            throw new IllegalArgumentException("city.getCity_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "DELETE FROM CITIES WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, city.getCityID());
            preparedStatement.execute();
            System.out.println("Deleted city: " + city);

        }
        catch (SQLException e ){
            e.printStackTrace();
        }
    }
}
