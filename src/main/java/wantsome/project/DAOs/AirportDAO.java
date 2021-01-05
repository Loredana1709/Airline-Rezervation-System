package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.AirportDTO;
import wantsome.project.DTOs.UserDTO;

import java.sql.*;

public class AirportDAO {

    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveAirport (AirportDTO airport) throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "INSERT INTO AIRPORTS (AIRPORT_NAME, CITY_ID) VALUES (?, ?)";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, airport.getAirportName());
            preparedStatement.setInt(2, airport.getCityID());
            preparedStatement.execute();
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
    }

    public AirportDTO getAirport (int id) throws SQLException {
        AirportDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM AIRPORTS WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    result = new AirportDTO(rs.getInt("ID"), rs.getString("AIRPORT_NAME"), rs.getInt("CITY_ID"));

                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }

    public void deleteAirport (AirportDTO airport) throws SQLException {
        if (airport.getAirportID() == null) {
            throw new IllegalArgumentException("airport.getAirport_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "DELETE FROM AIRPORTS WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, airport.getAirportID());
            preparedStatement.execute();
            System.out.println("Deleted airport: " + airport);

        }
        catch (SQLException e ){
            e.printStackTrace();
        }
    }
}
