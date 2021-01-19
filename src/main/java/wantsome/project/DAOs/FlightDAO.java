package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.AirportDTO;
import wantsome.project.DTOs.FlightDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {

    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveFlight (FlightDTO flight) {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "INSERT INTO FLIGHTS (FLIGHT_NUMBER, AIRLINE, FROM_AIRPORT_ID, " +
                "TO_AIRPORT_ID, DEPARTURE_DATE, ARRIVING_DATE)" +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getAirline());
            AirportDAO airportDAO = new AirportDAO();
            preparedStatement.setInt(3, airportDAO.getAirport(flight.getDepartureAirport())); //getAirport returns airportID using airportName
            preparedStatement.setInt(4, airportDAO.getAirport(flight.getArrivingAirport()));
            preparedStatement.setString(5, flight.getDepartureDate());
            preparedStatement.setString(6, flight.getArrivingDate());
            preparedStatement.execute();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }

    public FlightDTO getFlight (String flightNumber){
        FlightDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT f.ID, f.flight_number, f.airline, a.AIRPORT_NAME as \"from_airport\", \n" +
                "a2.AIRPORT_NAME as \"to_airport\", DEPARTURE_DATE, ARRIVING_DATE\n" +
                "from FLIGHTS f\n" +
                "join AIRPORTS a on a.ID = f.from_airport_id\n" +
                "join AIRPORTS a2 on a2.ID = f.TO_AIRPORT_ID\n" +
                "where f.FLIGHT_NUMBER = ?;";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, flightNumber);
            try(ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    result = new FlightDTO(rs.getInt("ID"),
                            rs.getString("FLIGHT_NUMBER"),
                            rs.getString("AIRLINE"),
                            rs.getString("from_airport"),
                            rs.getString("to_airport"),
                            rs.getString("departure_date"),
                            rs.getString("arriving_date"));
                }
            }

        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }

    public FlightDTO getFlight (int flightID){
        FlightDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM FLIGHTS WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, flightID);
            try(ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    result = new FlightDTO(rs.getInt("ID"),
                            rs.getString("FLIGHT_NUMBER"),
                            rs.getString("AIRLINE"),
                            rs.getString("FROM_AIRPORT_ID"),
                            rs.getString("TO_AIRPORT_ID"),
                            rs.getString("departure_date"),
                            rs.getString("arriving_date"));
                }
            }

        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }

    public List<FlightDTO> getAllFlights(){
        List<FlightDTO> flights = new ArrayList<>();
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT f.flight_number, f.airline, a.AIRPORT_NAME as \"from_airport\", \n" +
                "a2.AIRPORT_NAME as \"to_airport\", DEPARTURE_DATE, ARRIVING_DATE\n" +
                "from FLIGHTS f\n" +
                "join AIRPORTS a on a.ID = f.from_airport_id\n" +
                "join AIRPORTS a2 on a2.ID = f.TO_AIRPORT_ID;";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery(query)){
                while (rs.next()){
                    flights.add(new FlightDTO(rs.getString("FLIGHT_NUMBER"),
                            rs.getString("AIRLINE"),
                            rs.getString("from_airport"),
                            rs.getString("to_airport"),
                            rs.getString("departure_date"),
                            rs.getString("arriving_date")));
                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return flights;
    }

    public FlightDTO updateFlight (FlightDTO flight) {
        if (flight.getFlightID() == null){
            throw new IllegalArgumentException("flight.getFlight_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        AirportDAO airportDAO = new AirportDAO();

        String query = "UPDATE FLIGHTS SET FLIGHT_NUMBER = ?," +
                "AIRLINE = ?," +
                "FROM_AIRPORT_ID = ?," +
                "TO_AIRPORT_ID = ?," +
                "departure_date = ?," +
                "arriving_date = ?" +
                "WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getAirline());
            preparedStatement.setInt(3, airportDAO.getAirport(flight.getDepartureAirport())); //getAirport returns airportID using airportName
            preparedStatement.setInt(4, airportDAO.getAirport(flight.getArrivingAirport()));
            preparedStatement.setString(5, flight.getDepartureDate());
            preparedStatement.setString(6, flight.getArrivingDate());
            preparedStatement.setInt(7, flight.getFlightID());
            preparedStatement.execute();
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return flight;
    }

    public void deleteFlight (String flightNumber) {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "DELETE FROM FLIGHTS WHERE FLIGHT_NUMBER = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, flightNumber);

            preparedStatement.execute();
            System.out.println("Deleted flight with flight number: " + flightNumber);
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
    }


}
