package wantsome.project.DTOs;

public class AirportsDTO {

    private Integer airport_id;
    private Integer city_id;

    public AirportsDTO(Integer airport_id, Integer city_id) {
        this.airport_id = airport_id;
        this.city_id = city_id;
    }

    public Integer getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(Integer airport_id) {
        this.airport_id = airport_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(CitiesDTO city_id) {
        this.city_id = city_id.getCity_id();
    }

    @Override
    public String toString() {
        return "AirportsDTO{" +
                "airport_id=" + airport_id +
                ", city_id=" + city_id +
                '}';
    }
}
