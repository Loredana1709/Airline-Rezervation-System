package wantsome.project;

import wantsome.project.DAOs.FlightDAO;
import wantsome.project.DAOs.TicketDAO;
import wantsome.project.DAOs.UserDAO;
import wantsome.project.DTOs.FlightDTO;
import wantsome.project.DTOs.TicketDTO;
import wantsome.project.DTOs.UserDTO;

import java.sql.Date;
import java.sql.SQLOutput;
import java.sql.Time;

public class DBRunner {
    public static void main(String[] args) {
//        UserDAO userDAO = new UserDAO();
//
//        UserDTO dbUser = userDAO.getUser("Lore", "loredana");
//
//        dbUser.setFirstName("gigel");
//        dbUser = userDAO.updateUser(dbUser);


//        FlightDAO flightDAO = new FlightDAO();
//        FlightDTO flightDTO = flightDAO.getFlight("SJRY");
//        flightDAO.deleteFlight("SJRY");

        TicketDAO ticketDAO = new TicketDAO();
//        System.out.println(ticketDAO.getAllTicketsFromUser(8));

    }
}
