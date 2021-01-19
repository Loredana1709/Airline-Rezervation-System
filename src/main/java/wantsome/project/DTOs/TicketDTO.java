package wantsome.project.DTOs;

import java.util.Date;

public class TicketDTO {

    private Integer ticketID;
    private Integer userID;
    private String userFirstName;
    private String userLastName;
    private Integer flightID;
    private String flightNumber;
    private String airline;
    private String departureDate;
    private String arrivingDate;

    public TicketDTO(Integer ticketID, Integer userID, Integer flightID) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.flightID = flightID;

    }

    public TicketDTO(Integer userID, Integer flightID) {
        this.userID = userID;
        this.flightID = flightID;
    }

    public TicketDTO(Integer ticketID, String userFirstName, String userLastName,
                     String flightNumber, String airline, String departureDate) {
        this.ticketID = ticketID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureDate = departureDate;
    }


    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Integer getFlightID() {
        return flightID;
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivingDate() {
        return arrivingDate;
    }

    public void setArrivingDate(String arrivingDate) {
        this.arrivingDate = arrivingDate;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "ticketID=" + ticketID +
                ", userID=" + userID +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", flightID=" + flightID +
                ", flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivingDate='" + arrivingDate + '\'' +
                '}';
    }
}
