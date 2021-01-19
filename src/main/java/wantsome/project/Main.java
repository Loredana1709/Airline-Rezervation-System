package wantsome.project;

import spark.Request;
import spark.Response;
import wantsome.project.DAOs.AirportDAO;
import wantsome.project.DAOs.FlightDAO;
import wantsome.project.DAOs.TicketDAO;
import wantsome.project.DAOs.UserDAO;
import wantsome.project.DTOs.FlightDTO;
import wantsome.project.DTOs.TicketDTO;
import wantsome.project.DTOs.UserDTO;
import wantsome.project.web.SparkUtil;

import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;
import static wantsome.project.web.SparkUtil.render;

/**
 * Main class of the application. Using Spark framework.
 */
public class Main {

    public static void main(String[] args) {
        setup();
        configureRoutesAndStart();
    }

    private static void setup() {
        //create and configure all needed resources (like db tables, sample data, etc)
    }

    private static void configureRoutesAndStart() {
        staticFileLocation("public");

        //configure all routes
        get("/main", Main::getMainWebPage);
        get("/login", Main::login);
        post("/login", Main::handleLogin);
        get("/signIn", Main::signIn);
        post("/signIn", Main::handleSignUp);
        get("/orderflight", Main::orderFlight);
        get("/booking/:flightNumber", Main::bookTicket);
        post("/booking/:flightNumber", Main::handleBookedTicket);
        get("/history", Main::ticketHistory);



        get("/editflight", Main::editFlight);
        get("/delete/:flightNumber", Main::confirmDeletedFlight);
        post("/delete/:flightNumber", Main::handleConfirmDeletedFlight);
        post("/update/:flightNumber", Main::handleUpdateFlight);
        get("/update/:flightNumber", Main::updateFlight);
        post("/createFlight", Main::handleCreateFlight);
        get("/createFlight", Main::createFlight);

        awaitInitialization();
        System.out.println("\nServer started: http://localhost:4567/main");
    }

    //example of returning a web page
    private static Object getMainWebPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("serverTime", new Date().toString());
        return SparkUtil.render("main.vm", model);
    }

    private static Object login(Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        return SparkUtil.render("login.vm", model);
    }

    private static Object handleLogin (Request request, Response response){
        String userName = request.queryParams("userName");
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        request.session().attribute("userName", userName);
        request.session().attribute("email", email);
        request.session().attribute("password", password);
        request.session().maxInactiveInterval(600000);

        UserDTO loggedUser = new UserDTO(userName, email, password);
        UserDAO userDAO = new UserDAO();

        if (userDAO.checkUserInDB(loggedUser)){
            if (userDAO.checkIfAdmin(loggedUser)){
                response.redirect("/editflight");
            } else{
                response.redirect("/orderflight");

            }
        } return errorLogin("This account does not exist. Try to create a new account");
    }

    private static Object signIn(Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        return SparkUtil.render("createAccount.vm", model);
    }

    private static Object handleSignUp(Request request, Response response){
        String firstName = request.queryParams("first_name");
        String lastName = request.queryParams("last_name");
        String userName = request.queryParams("user_name");
        String password = request.queryParams("password");
        String email = request.queryParams("email");
        String phone = request.queryParams("phone");
        String address = request.queryParams("address");

        request.session().attribute("userName", userName);
        request.session().maxInactiveInterval(600000);

        UserDTO signedUser = new UserDTO(firstName, lastName, userName, password, email,
                Integer.parseInt(phone), address, "customer");
        UserDAO userDAO = new UserDAO();

        if(userDAO.getUser(userName) == null){
            userDAO.saveUser(signedUser);
            System.out.println("Saved user: " + signedUser);
            response.redirect("/orderflight");
        }
        return errorSignUp("Account with username " + userName + " could not be created!");
    }

    private static Object orderFlight(Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        String user = request.session().attribute("userName");
        model.put("user", user);

        FlightDAO flightDAO = new FlightDAO();
        List<FlightDTO> flightsDTO = new ArrayList<>(flightDAO.getAllFlights());
        model.put("flights", flightsDTO);

        return SparkUtil.render("orderflight.vm", model);
    }

    private static Object handleBookedTicket (Request request, Response response){
        String flightNumber = request.params(":flightNumber");
        String confirm = request.queryParams("confirm");

        FlightDAO flightDAO = new FlightDAO();
        FlightDTO flight = flightDAO.getFlight(flightNumber);

        UserDAO userDAO = new UserDAO();
        String userName = request.session().attribute("userName");
        String password = request.session().attribute("password");
        UserDTO user = userDAO.getUser(userName, password);

        TicketDAO ticketDAO = new TicketDAO();
        TicketDTO bockedTicket = new TicketDTO(user.getUserID(), flight.getFlightID());

        List<FlightDTO> flights = new ArrayList<>(flightDAO.getAllFlights());

        if(confirm.toLowerCase().equals("yes")){
            for (FlightDTO f : flights){
                if(f.getFlightNumber().equals(flightNumber)){
                    ticketDAO.saveTicket(bockedTicket);
                    response.redirect("/history");
                }
            }
        }
        else if(confirm.toLowerCase().equals("no")){
            response.redirect("/orderflight");
        }
        return "An error has occurred";
    }

    private static Object bookTicket (Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        String userName = request.session().attribute("userName");
        model.put("username", userName);

        String password = request.session().attribute("password");
        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.getUser(userName, password);
        model.put("user", user);


        String flightNumber = request.params(":flightNumber");
        FlightDAO flightDAO = new FlightDAO();
        FlightDTO flight = flightDAO.getFlight(flightNumber);
        model.put("flight", flight);

        return SparkUtil.render("bookticket.vm", model);
    }

    private static Object ticketHistory (Request request, Response response){
        Map<String, Object> model = new HashMap<>();

        String userName = request.session().attribute("userName");
        String password = request.session().attribute("password");
        model.put("user", userName);

        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.getUser(userName, password);

        TicketDAO ticketDAO = new TicketDAO();
        List<TicketDTO> tickets = new ArrayList<>(ticketDAO.getAllTicketsFromUser(user.getUserID()));
        model.put("tickets", tickets);

        return SparkUtil.render("ticketHistory.vm", model);
    }



    private static Object editFlight(Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        String user = request.session().attribute("userName");
        model.put("user", user);

        FlightDAO ticketDAO = new FlightDAO();
        List<FlightDTO> flightsDTO = new ArrayList<>(ticketDAO.getAllFlights());
        model.put("flights", flightsDTO);

        return SparkUtil.render("editflight.vm", model);
    }

    private static Object confirmDeletedFlight (Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        String user = request.session().attribute("userName");
        model.put("user", user);

        String flightNumber = request.params(":flightNumber");
        FlightDAO flightDAO = new FlightDAO();
        FlightDTO flight = flightDAO.getFlight(flightNumber);
        model.put("flight", flight);

        return SparkUtil.render("deleteFlight.vm", model);
    }

    private static Object handleConfirmDeletedFlight (Request request, Response response){
        String flightNumber = request.params(":flightNumber");
        String confirm = request.queryParams("confirm");

        FlightDAO flightDAO = new FlightDAO();
        FlightDTO flight = flightDAO.getFlight(flightNumber);

        TicketDAO ticketDAO = new TicketDAO();
        TicketDTO ticket = ticketDAO.getTicket(flight.getFlightID());

        if (confirm.toLowerCase().equals("yes")){
            if(ticketDAO.getTicket(flight.getFlightID()) != null){
                ticketDAO.deleteTicket(ticket);
                flightDAO.deleteFlight(flight.getFlightNumber());
                response.redirect("/editflight");
            }else if(ticketDAO.getTicket(flight.getFlightID()) == null){
                flightDAO.deleteFlight(flight.getFlightNumber());
                response.redirect("/editflight");
            }
        } else if(confirm.toLowerCase().equals("no")){
            response.redirect("/editflight");
        }
        return "An error has occurred";    }

    private static Object updateFlight (Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        String user = request.session().attribute("userName");
        model.put("user", user);

        String flightNumber = request.params(":flightNumber");
        FlightDAO flightDAO = new FlightDAO();
        FlightDTO flight = flightDAO.getFlight(flightNumber);
        model.put("flight", flight);

        return SparkUtil.render("updateFlight.vm", model);
    }

    private static Object handleUpdateFlight (Request request, Response response){
        String flightNumber = request.params(":flightNumber");
        FlightDAO flightDAO = new FlightDAO();
        FlightDTO flight = flightDAO.getFlight(flightNumber);

        String flightNo = request.queryParams("flightNumber");
        String airline = request.queryParams("airline");
        String departure = request.queryParams("departure");
        String destination = request.queryParams("destination");
        String departureDate = request.queryParams("departureDate");
        String arrivingDate = request.queryParams("arrivingDate");

        flight.setFlightNumber(flightNo);
        flight.setAirline(airline);
        flight.setDepartureAirport(departure);
        flight.setArrivingAirport(destination);
        flight.setDepartureDate(departureDate);
        flight.setArrivingDate(arrivingDate);
        flightDAO.updateFlight(flight);

        response.redirect("/editflight");
        return "Successfully updated!";
    }

    private static Object createFlight (Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        String user = request.session().attribute("userName");
        model.put("user", user);

        AirportDAO airportDAO = new AirportDAO();
        List<String> airportsName = airportDAO.getAllAirports();
        model.put("airports", airportsName);

        return SparkUtil.render("createFlight.vm", model);
    }

    private static Object handleCreateFlight (Request request, Response response) {
        FlightDAO flightDAO = new FlightDAO();

        String flightNo = request.queryParams("flightNumber");
        String airline = request.queryParams("airline");
        String departure = request.queryParams("departure");
        String destination = request.queryParams("destination");
        String departureDate = request.queryParams("departureDate");
        String arrivingDate = request.queryParams("arrivingDate");

        FlightDTO flight = new FlightDTO(flightNo, airline, departure, destination,
                departureDate, arrivingDate);
        flightDAO.saveFlight(flight);
        response.redirect("/editflight");
        return "Successfully created!";
    }






    private static String errorLogin(String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", message);
        return render("login.vm", model);
    }

    private static String errorSignUp(String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", message);
        return render("createAccount.vm", model);
    }

    private static String errorSummaryInfoCustomer(String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", message);
        return render("createAccount.vm", model);
    }

    private static String errorDeletedFlight (String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", message);
        return render("deleteFlight.vm", model);
    }

    private static String errorCreateFlight (String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", message);
        return render("createFlight.vm", model);
    }


}
