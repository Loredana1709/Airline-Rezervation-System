package wantsome.project.DTOs;

import java.sql.Time;

public class FlightsDTO {

    private Integer flight_id;
    private String flight_number;
    private String flight_name;
    private Integer from_airport_id;
    private Integer to_airport_id;
    private Time departure_time;
    private Time arriving_time;
    private Long travel_duration;

    public FlightsDTO(Integer flight_id, String flight_number, String flight_name, Integer from_airport_id,
                      Integer to_airport_id, Time departure_time, Time arriving_time, Long travel_duration) {
        this.flight_id = flight_id;
        this.flight_number = flight_number;
        this.flight_name = flight_name;
        this.from_airport_id = from_airport_id;
        this.to_airport_id = to_airport_id;
        this.departure_time = departure_time;
        this.arriving_time = arriving_time;
        this.travel_duration = travel_duration;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(Integer flight_id) {
        this.flight_id = flight_id;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public void setFlight_number(String flight_number) {
        this.flight_number = flight_number;
    }

    public String getFlight_name() {
        return flight_name;
    }

    public void setFlight_name(String flight_name) {
        this.flight_name = flight_name;
    }

    public Integer getFrom_airport_id() {
        return from_airport_id;
    }

    public void setFrom_airport_id(AirportsDTO from_airport_id) {
        this.from_airport_id = from_airport_id.getAirport_id();
    }

    public Integer getTo_airport_id() {
        return to_airport_id;
    }

    public void setTo_airport_id(AirportsDTO to_airport_id) {
        this.to_airport_id = to_airport_id.getAirport_id();
    }

    public Time getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Time departure_time) {
        this.departure_time = departure_time;
    }

    public Time getArriving_time() {
        return arriving_time;
    }

    public void setArriving_time(Time arriving_time) {
        this.arriving_time = arriving_time;
    }

    public Long getTravel_duration() {
        return travel_duration;
    }

    public void setTravel_duration(Long travel_duration) {

        this.travel_duration = departure_time.getTime() - arriving_time.getTime();
    }

    @Override
    public String toString() {
        return "FlightsDTO{" +
                "flight_id=" + flight_id +
                ", flight_number='" + flight_number + '\'' +
                ", flight_name='" + flight_name + '\'' +
                ", from_airport_id=" + from_airport_id +
                ", to_airport_id=" + to_airport_id +
                ", departure_time=" + departure_time +
                ", arriving_time=" + arriving_time +
                ", travel_duration=" + travel_duration +
                '}';
    }
}
