package wantsome.project.DTOs;

import java.sql.Date;
import java.sql.Time;

public class FlightDTO {

    private Integer flightID;
    private String flightNumber;
    private String airline;
    private String fromAirportName;
    private String toAirportName;
    private Date flightDate;
    private Time departureTime;
    private Time arrivingTime;

    public FlightDTO(Integer flightID, String flightNumber, String airline, String fromAirportName,
                     String toAirportName, Date flightDate, Time departureTime, Time arrivingTime) {
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.fromAirportName = fromAirportName;
        this.toAirportName = toAirportName;
        this.flightDate = flightDate;
        this.departureTime = departureTime;
        this.arrivingTime = arrivingTime;
    }

    public FlightDTO(String flightNumber, String airline, String fromAirportName,
                     String toAirportName, Date flightDate, Time departureTime, Time arrivingTime) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.fromAirportName = fromAirportName;
        this.toAirportName = toAirportName;
        this.flightDate = flightDate;
        this.departureTime = departureTime;
        this.arrivingTime = arrivingTime;
    }

    public Integer getFlightID() {
        return flightID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getFromAirportName() {
        return fromAirportName;
    }

    public String getToAirportName() {
        return toAirportName;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public Time getArrivingTime() {
        return arrivingTime;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "flightID=" + flightID +
                ", flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", fromAirportName='" + fromAirportName + '\'' +
                ", toAirportName='" + toAirportName + '\'' +
                ", flightDate=" + flightDate +
                ", departureTime=" + departureTime +
                ", arrivingTime=" + arrivingTime +
                '}';
    }
}
