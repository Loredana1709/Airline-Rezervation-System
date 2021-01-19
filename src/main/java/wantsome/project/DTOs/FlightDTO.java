package wantsome.project.DTOs;


public class FlightDTO {

    private Integer flightID;
    private String flightNumber;
    private String airline;
    private String departureAirport;
    private String arrivingAirport;
    private String departureDate;
    private String arrivingDate;

    public FlightDTO(Integer flightID, String flightNumber, String airline, String departureAirport,
                     String arrivingAirport, String departureDate, String arrivingDate) {
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivingAirport = arrivingAirport;
        this.departureDate = departureDate;
        this.arrivingDate = arrivingDate;
    }

    public FlightDTO(String flightNumber, String airline, String departureAirport,
                     String arrivingAirport, String departureDate, String arrivingDate) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureAirport = departureAirport;
        this.arrivingAirport = arrivingAirport;
        this.departureDate = departureDate;
        this.arrivingDate = arrivingDate;
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

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivingAirport() {
        return arrivingAirport;
    }

    public void setArrivingAirport(String arrivingAirport) {
        this.arrivingAirport = arrivingAirport;
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
        return "FlightDTO{" +
                "flightID=" + flightID +
                ", flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivingAirport='" + arrivingAirport + '\'' +
                ", departureDate='" + departureDate + '\'' +
                ", arrivingDate='" + arrivingDate + '\'' +
                '}';
    }
}
