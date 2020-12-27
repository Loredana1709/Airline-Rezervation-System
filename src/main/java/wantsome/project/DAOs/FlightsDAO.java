package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.FlightsDTO;

import java.sql.*;

public class FlightsDAO {

    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveFlight (FlightsDTO flightDTO) throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "INSERT INTO FLIGHTS (FLIGHT_NUMBER, FLIGHT_NAME, FROM_AIRPORT_ID, TO_AIRPORT_ID, DEPARTURE_TIME, ARRIVING_TIME, TRAVEL_DURATION)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, flightDTO.getFlight_number());
        preparedStatement.setString(2, flightDTO.getFlight_name());
        preparedStatement.setInt(3, flightDTO.getFrom_airport_id());
        preparedStatement.setInt(4, flightDTO.getTo_airport_id());
        preparedStatement.setTime(5, flightDTO.getDeparture_time());
        preparedStatement.setTime(6, flightDTO.getArriving_time());
        preparedStatement.setLong(7, flightDTO.getTravel_duration());
        preparedStatement.execute();

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    public FlightsDTO getFlight (int id) throws SQLException {
        FlightsDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "SELECT * FROM FLIGHTS WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            result = new FlightsDTO(rs.getInt("ID"),
                    rs.getString("FLIGHT_NUMBER"),
                    rs.getString("FLIGHT_NAME"),
                    rs.getInt("FROM_AIRPORT_ID"),
                    rs.getInt("TO_AIRPORT_ID"),
                    rs.getTime("DEPARTURE_TIME"),
                    rs.getTime("ARRIVING_TIME"),
                    rs.getLong("TRAVEL_DURATION"));
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return result;
    }

    public FlightsDTO updateFlight (FlightsDTO flightDTO) throws SQLException {
        if (flightDTO.getFlight_id() == null){
            throw new IllegalArgumentException("flight.getFlight_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "UPDATE FLIGHTS SET FLIGHT_NUMBER = ?" +
                "FLIGHT_NAME = ?" +
                "FROM_AIRPORT_ID = ?" +
                "TO_AIRPORT_ID = ?" +
                "DEPARTURE_TIME = ?" +
                "ARRIVING_TIME = ?" +
                "TRAVEL_DURATION = ?" +
                "WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, flightDTO.getFlight_number());
        preparedStatement.setString(2, flightDTO.getFlight_name());
        preparedStatement.setInt(3, flightDTO.getFrom_airport_id());
        preparedStatement.setInt(4, flightDTO.getTo_airport_id());
        preparedStatement.setTime(5, flightDTO.getDeparture_time());
        preparedStatement.setTime(6, flightDTO.getArriving_time());
        preparedStatement.setLong(7, flightDTO.getTravel_duration());
        preparedStatement.setInt(8, flightDTO.getFlight_id());

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return flightDTO;
    }

    public void deleteFlight (FlightsDTO flightDTO) throws SQLException {
        if (flightDTO.getFlight_id() == null){
            throw new IllegalArgumentException("flight.getFlight_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "DELETE FROM FLIGHTS WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, flightDTO.getFlight_id());

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

}
