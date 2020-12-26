package wantsome.project.DTOs;

public class TicketsDTO {

    private Integer ticket_id;
    private Integer customer_id;
    private Integer flight_id;
    private Double ticket_price;

    public TicketsDTO(Integer ticket_id, Integer customer_id, Integer flight_id, Double ticket_price) {
        this.ticket_id = ticket_id;
        this.customer_id = customer_id;
        this.flight_id = flight_id;
        this.ticket_price = ticket_price;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Integer ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(UsersDTO customer_id) {
        this.customer_id = customer_id.getUser_id();
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(FlightsDTO flight_id) {
        this.flight_id = flight_id.getFlight_id();
    }

    public Double getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(Double ticket_price) {
        this.ticket_price = ticket_price;
    }

    @Override
    public String toString() {
        return "TicketsDTO{" +
                "ticket_id=" + ticket_id +
                ", customer_id=" + customer_id +
                ", flight_id=" + flight_id +
                ", ticket_price=" + ticket_price +
                '}';
    }
}
