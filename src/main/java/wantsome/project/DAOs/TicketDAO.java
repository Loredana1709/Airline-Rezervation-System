package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.TicketDTO;

import java.sql.*;

public class TicketDAO {
    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveTicket (TicketDTO ticket){
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "INSERT INTO TICKETS (CUSTOMER_ID, FLIGHT_ID, TICKET_PRICE) VALUES (?, ?, ?)";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, ticket.getUserID());
            preparedStatement.setInt(2, ticket.getFlightID());
            preparedStatement.setDouble(3, ticket.getTicketPrice());
            preparedStatement.execute();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }

    public TicketDTO getTicket (int id) {
        TicketDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        String query = "SELECT * FROM TICKETS WHERE ID = ?";

        try(Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()){
                while (rs.next()){
                    result = new TicketDTO(rs.getInt("ID"),
                            rs.getInt("CUSTOMER_ID"),
                            rs.getInt("FLIGHT_ID"),
                            rs.getDouble("TICKET_PRICE"));
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

        String query = "DELETE FROM TICKETS WHERE CUSTOMER_ID = ? AND FLIGHT_ID = ?";

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
