package Locations;

import jakarta.persistence.*;


@Entity
@Table(name = "location")

public class Location {
    @Id
   @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name="city", nullable = false)

    private String city;

    @Column(name="country", nullable = false)
    private String country;


    @Column(name="region")
    private String region;

    @Column(name="longitude", nullable = false)
    private Double longitude;

    @Column(name="latitude", nullable = false)
    private Double latitude;

    @Column(name="temperature", nullable = false)
    private Double temperature;

    @Column(name="pressure", nullable = false)
    private Double pressure;

    @Column(name="humidity", nullable = false)
    private Double humidity;

    @Column(name="wind_direction")
    private Double wind_direction;

    @Column(name="wind_speed", nullable = false)
    private Double wind_speed;


    public Location() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(Double wind_direction) {
        this.wind_direction = wind_direction;
    }

    public Double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(Double wind_speed) {
        this.wind_speed = wind_speed;
    }
}