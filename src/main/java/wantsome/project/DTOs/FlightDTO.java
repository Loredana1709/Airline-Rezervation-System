package wantsome.project.DTOs;

import java.sql.Date;
import java.sql.Time;
import java.util.PrimitiveIterator;

public class FlightDTO {

    private Integer flightID;
    private String flightNumber;
    private String airline;
    private Integer fromAirportID;
    private Integer toAirportID;
    private Date flightDate;
    private Time departureTime;
    private Time arrivingTime;

    public FlightDTO(Integer flightID, String flightNumber, String airline, Integer fromAirportID,
                     Integer toAirportID, Date flightDate, Time departureTime, Time arrivingTime) {
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.fromAirportID = fromAirportID;
        this.toAirportID = toAirportID;
        this.flightDate = flightDate;
        this.departureTime = departureTime;
        this.arrivingTime = arrivingTime;
    }

    public FlightDTO(String flightNumber, String airline, Integer fromAirportID,
                     Integer toAirportID, Date flightDate, Time departureTime, Time arrivingTime) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.fromAirportID = fromAirportID;
        this.toAirportID = toAirportID;
        this.flightDate = flightDate;
        this.departureTime = departureTime;
        this.arrivingTime = arrivingTime;
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

    public Integer getFromAirportID() {
        return fromAirportID;
    }

    public void setFromAirportID(Integer fromAirportID) {
        this.fromAirportID = fromAirportID;
    }

    public Integer getToAirportID() {
        return toAirportID;
    }

    public void setToAirportID(Integer toAirportID) {
        this.toAirportID = toAirportID;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(Time arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "flightID=" + flightID +
                ", flightNumber='" + flightNumber + '\'' +
                ", airline='" + airline + '\'' +
                ", fromAirportID=" + fromAirportID +
                ", toAirportID=" + toAirportID +
                ", flightDate=" + flightDate +
                ", departureTime=" + departureTime +
                ", arrivingTime=" + arrivingTime +
                '}';
    }
}
