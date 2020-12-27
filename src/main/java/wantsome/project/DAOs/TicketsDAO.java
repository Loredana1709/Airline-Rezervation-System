package wantsome.project.DAOs;

import org.sqlite.SQLiteConfig;
import wantsome.project.DTOs.TicketsDTO;

import java.sql.*;

public class TicketsDAO {
    private static final String databaseUrl = "jdbc:sqlite:C:\\Users\\cobzarl\\Desktop\\Wantsome\\Java\\DBeaver\\airlinerezervationsystem";

    public void saveTicket (TicketsDTO ticket) throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "INSERT INTO TICKETS (CUSTOMER_ID, FLIGHT_ID, TICKET_PRICE) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, ticket.getCustomer_id());
        preparedStatement.setInt(2, ticket.getFlight_id());
        preparedStatement.setDouble(3, ticket.getTicket_price());
        preparedStatement.execute();

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }

    public TicketsDTO getTicket (int id) throws SQLException {
        TicketsDTO result = null;
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "SELECT * FROM TICKETS WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            result = new TicketsDTO(rs.getInt("ID"),
                    rs.getInt("CUSTOMER_ID"),
                    rs.getInt("FLIGHT_ID"),
                    rs.getDouble("TICKET_PRICE"));
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return result;
    }

    public TicketsDTO updateTicket (TicketsDTO ticket) throws SQLException {
        if (ticket.getTicket_id() == null) {
            throw new IllegalArgumentException("ticket.getTicket_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "UPDATE TICKETS SET CUSTOMER_ID = ?, FLIGHT_ID = ?, TICKET_PRICE = ? WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, ticket.getCustomer_id());
        preparedStatement.setInt(2, ticket.getFlight_id());
        preparedStatement.setDouble(3, ticket.getTicket_price());
        preparedStatement.setInt(4, ticket.getTicket_id());

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
        return ticket;
    }

    public void deleteTicket (TicketsDTO ticket) throws SQLException {
        if (ticket.getTicket_id() == null) {
            throw new IllegalArgumentException("ticket.getTicket_id() is null");
        }
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        Connection connection = DriverManager.getConnection(databaseUrl, config.toProperties());
        String query = "DELETE FROM TICKETS WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, ticket.getTicket_id());

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}
