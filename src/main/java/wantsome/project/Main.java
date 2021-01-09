package wantsome.project;

import spark.Request;
import spark.Response;
import wantsome.project.DAOs.FlightDAO;
import wantsome.project.DAOs.UserDAO;
import wantsome.project.DTOs.FlightDTO;
import wantsome.project.DTOs.UserDTO;
import wantsome.project.web.SparkUtil;

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
        get("/signIn", Main::createAccount);
        post("/signIn", Main::handleSignIn);
        post("/login/book", Main::handleBook);

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
        UserDTO loggedUser = new UserDTO(userName, email, password);
        UserDAO userDAO = new UserDAO();

        if (userDAO.checkUserInDB(loggedUser)){
            if (userDAO.checkIfAdmin(loggedUser)){
                return helloUserEdit(userName);
            } else{
                return helloUserOrder(userName);

            }
        } return errorMessage("This account does not exist. Try to create a new account");
    }

    private static String helloUserOrder(String user){
        Map<String, Object> model = new HashMap<>();
        model.put("username", user);

        FlightDAO ticketDAO = new FlightDAO();
        List<FlightDTO> flightsDTO = new ArrayList<>(ticketDAO.getAllFlights());
        model.put("flights", flightsDTO);

        return SparkUtil.render("orderticket.vm", model);
    }

    private static Object helloUserEdit (String user){
        Map<String, Object> model = new HashMap<>();
        model.put("username", user);
        return SparkUtil.render("editticket.vm", model);
    }

    private static Object createAccount(Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        return SparkUtil.render("createAccount.vm", model);
    }

    private static Object handleSignIn (Request request, Response response){
        String firstName = request.queryParams("first_name");
        String lastName = request.queryParams("last_name");
        String userName = request.queryParams("user_name");
        String password = request.queryParams("password");
        String email = request.queryParams("email");
        String phone = request.queryParams("phone");
        String address = request.queryParams("address");

        UserDTO signedUser = new UserDTO(firstName, lastName, userName, password, email,
                Integer.parseInt(phone), address, "customer");
        UserDAO userDAO = new UserDAO();

        if(userDAO.getUser(userName) == null){
            userDAO.saveUser(signedUser);
            System.out.println("Saved user: " + signedUser);
            return helloUserOrder(userName);
        }
        return errorMessage("Account with username " + userName + "could not be created!");
    }

    private static String errorMessage(String message) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", message);
        return render("login.vm", model);
    }

    private static Object handleBook (Request request, Response response){
        String flight = request.queryParams("checked");
        return checked(flight);
    }

    public static String checked (String flight){
        Map<String, Object> model = new HashMap<>();
        model.put("flight", flight);
        return SparkUtil.render("book.vm", model);
    }
}
