package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.FlightDTO;

import java.sql.*;

public class FlightDAO {

    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveFlight (FlightDTO flight) {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "INSERT INTO FLIGHTS (FLIGHT_NUMBER, AIRLINE, FROM_AIRPORT_ID, " +
                "TO_AIRPORT_ID, FLIGHT_DATE, DEPARTURE_TIME, ARRIVING_TIME)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getAirline());
            preparedStatement.setInt(3, flight.getFromAirportID());
            preparedStatement.setInt(4, flight.getToAirportID());
            preparedStatement.setDate(5, flight.getFlightDate());
            preparedStatement.setTime(6, flight.getDepartureTime());
            preparedStatement.setTime(7, flight.getArrivingTime());
            preparedStatement.execute();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }

    public FlightDTO getFlight (int id) throws SQLException {
        FlightDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM FLIGHTS WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    result = new FlightDTO(rs.getInt("ID"),
                            rs.getString("FLIGHT_NUMBER"),
                            rs.getString("AIRLINE"),
                            rs.getInt("FROM_AIRPORT_ID"),
                            rs.getInt("TO_AIRPORT_ID"),
                            rs.getDate("FLIGHT_DATE"),
                            rs.getTime("DEPARTURE_TIME"),
                            rs.getTime("ARRIVING_TIME"));
                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }

    public FlightDTO updateFlight (FlightDTO flight) {
        if (flight.getFlightID() == null){
            throw new IllegalArgumentException("flight.getFlight_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "UPDATE FLIGHTS SET FLIGHT_NUMBER = ?" +
                "FLIGHT_NAME = ?" +
                "FROM_AIRPORT_ID = ?" +
                "TO_AIRPORT_ID = ?" +
                "FLIGHT_DATE = ?" +
                "DEPARTURE_TIME = ?" +
                "ARRIVING_TIME = ?" +
                "WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getAirline());
            preparedStatement.setInt(3, flight.getFromAirportID());
            preparedStatement.setInt(4, flight.getToAirportID());
            preparedStatement.setDate(5, flight.getFlightDate());
            preparedStatement.setTime(6, flight.getDepartureTime());
            preparedStatement.setTime(7, flight.getArrivingTime());
            preparedStatement.setInt(8, flight.getFlightID());
            preparedStatement.execute();
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return flight;

    }

    public void deleteFlight (FlightDTO flight) {
        if (flight.getFlightID() == null){
            throw new IllegalArgumentException("flight.getFlight_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "DELETE FROM FLIGHTS WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, flight.getFlightID());

            preparedStatement.execute();
            System.out.println("Deleted flight: " + flight);

        }
        catch (SQLException e ){
            e.printStackTrace();
        }
    }

}
