package wantsome.project.DTOs;

public class TicketDTO {

    private Integer ticketID;
    private Integer userID;
    private Integer flightID;
    private Double ticketPrice;

    public TicketDTO(Integer ticketID, Integer userID, Integer flightID, Double ticketPrice) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.flightID = flightID;
        this.ticketPrice = ticketPrice;
    }

    public TicketDTO(Integer userID, Integer flightID, Double ticketPrice) {
        this.userID = userID;
        this.flightID = flightID;
        this.ticketPrice = ticketPrice;
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

    public Integer getFlightID() {
        return flightID;
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }

    public Double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return "TicketsDTO{" +
                "ticketID=" + ticketID +
                ", userID=" + userID +
                ", flightID=" + flightID +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
