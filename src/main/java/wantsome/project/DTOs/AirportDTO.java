package wantsome.project.DTOs;

public class AirportDTO {

    private Integer airportID;
    private String airportName;
    private Integer cityID;

    public AirportDTO(Integer airportID, String airportName, Integer cityID) {
        this.airportID = airportID;
        this.airportName = airportName;
        this.cityID = cityID;
    }

    public Integer getAirportID() {
        return airportID;
    }

    public void setAirportID(Integer airportID) {
        this.airportID = airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    @Override
    public String toString() {
        return "AirportDTO{" +
                "airportID=" + airportID +
                ", airportName='" + airportName + '\'' +
                ", cityID=" + cityID +
                '}';
    }
}
