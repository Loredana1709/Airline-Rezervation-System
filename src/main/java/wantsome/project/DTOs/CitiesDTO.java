package wantsome.project.DTOs;

public class CitiesDTO {

    private Integer city_id;
    private String name;

    public CitiesDTO(Integer city_id, String name) {
        this.city_id = city_id;
        this.name = name;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CitiesDTO{" +
                "city_id=" + city_id +
                ", name='" + name + '\'' +
                '}';
    }
}
