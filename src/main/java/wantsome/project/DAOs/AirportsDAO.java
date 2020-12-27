package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.AirportsDTO;

import java.sql.*;
import java.util.WeakHashMap;

public class AirportsDAO {

    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveAirport (AirportsDTO airport) throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "INSERT INTO AIRPORTS (CITY_ID) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, airport.getCity_id());
        preparedStatement.execute();

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    public AirportsDTO getAirport (int id) throws SQLException {
        AirportsDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "SELECT * FROM AIRPORTS WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            result = new AirportsDTO(rs.getInt("ID"), rs.getInt("CITY_ID"));
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return result;
    }

    public AirportsDTO updateAirport (AirportsDTO airport) throws SQLException {
        if (airport.getAirport_id() == null) {
            throw new IllegalArgumentException("airport.getAirport_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "UPDATE AIRPORTS SET CITY_ID = ? WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, airport.getCity_id());
        preparedStatement.setInt(2, airport.getAirport_id());

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return airport;
    }

    public void deleteAirport (AirportsDTO airport) throws SQLException {
        if (airport.getAirport_id() == null) {
            throw new IllegalArgumentException("airport.getAirport_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "DELETE FROM AIRPORTS WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // the order of the parameters must match the order of the columns mentioned in the query
        preparedStatement.setInt(1, airport.getAirport_id());

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}
