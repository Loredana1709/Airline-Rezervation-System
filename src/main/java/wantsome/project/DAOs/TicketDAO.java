package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.FlightDTO;
import wantsome.project.DTOs.TicketDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveTicket (TicketDTO ticket){
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "INSERT INTO TICKETS (USER_ID, FLIGHT_ID) VALUES (?, ?)";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, ticket.getUserID());
            preparedStatement.setInt(2, ticket.getFlightID());
            preparedStatement.execute();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }

    public List<TicketDTO> getAllTicketsFromUser(int userID) {
        List<TicketDTO> tickets = new ArrayList<>();
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT t.ID, u.first_name, u.last_name, f.flight_number, f.AIRLINE, f.DEPARTURE_DATE, f.ARRIVING_DATE \n" +
                "FROM TICKETS t\n" +
                "join USERS u on t.USER_ID = u.ID \n" +
                "join FLIGHTS f on t.FLIGHT_ID = f.ID\n" +
                "where t.USER_ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, userID);
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    tickets.add(new TicketDTO(rs.getInt("ID"),
                            rs.getString("FIRST_NAME"),
                            rs.getString("LAST_NAME"),
                            rs.getString("FLIGHT_NUMBER"),
                            rs.getString("AIRLINE"),
                            rs.getString("DEPARTURE_DATE")));
                }
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return tickets;
    }

    public TicketDTO getTicket (int flightID){
        TicketDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM TICKETS WHERE FLIGHT_ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, flightID);
            try(ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    result = new TicketDTO(rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("flight_id"));
                }
            }

        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return result;
    }


    public void deleteTicket (TicketDTO ticket) {
        if (ticket.getUserID() == null || ticket.getFlightID() == null) {
            throw new IllegalArgumentException("ticket.getCustomer_id() OR ticket.getFlight_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "DELETE FROM TICKETS WHERE USER_ID = ? AND FLIGHT_ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, ticket.getUserID());
            preparedStatement.setInt(2, ticket.getFlightID());
            preparedStatement.execute();
            System.out.println("Deleted ticket: " + ticket);

        }catch (SQLException e ){
            e.printStackTrace();
        }
    }
}
