package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.AirportDTO;
import wantsome.project.DTOs.FlightDTO;
import wantsome.project.DTOs.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public int getAirport (String airportName) throws SQLException {
        int airportID = 0;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM AIRPORTS WHERE AIRPORT_NAME = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, airportName);
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    airportID = rs.getInt("ID");

                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return airportID;
    }

    public List<String> getAllAirports(){
        List<String> airportsName = new ArrayList<>();
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT AIRPORT_NAME FROM AIRPORTS";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery(query)){
                while (rs.next()){
                    airportsName.add(rs.getString("AIRPORT_NAME"));
                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return airportsName;
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
