package wantsome.project.DTOs;

public class CityDTO {

    private Integer cityID;
    private String name;

    public CityDTO(Integer cityID, String name) {
        this.cityID = cityID;
        this.name = name;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "cityID=" + cityID +
                ", name='" + name + '\'' +
                '}';
    }
}
