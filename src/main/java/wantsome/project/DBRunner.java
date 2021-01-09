package wantsome.project;

import wantsome.project.DAOs.FlightDAO;
import wantsome.project.DAOs.UserDAO;
import wantsome.project.DTOs.FlightDTO;
import wantsome.project.DTOs.UserDTO;

public class DBRunner {
    public static void main(String[] args) {
//        UserDTO user = new UserDTO("loredana121@gmail.com", "sdhejr");
//        UserDAO userDAO = new UserDAO();
//        System.out.println(userDAO.checkUserInDB(user));
        FlightDAO flightDAO = new FlightDAO();
        System.out.println(flightDAO.getAllFlights());
    }
}
