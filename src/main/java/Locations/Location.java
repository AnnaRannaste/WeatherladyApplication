package Locations;

import jakarta.persistence.*;


@Entity
@Table(name = "location")

public class Location {
 //   @Id
 //  @Column(name = "id", unique = true, nullable = false)
//    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date", unique = true)
    private String date;

    @Column(name="city", nullable = false)

    private String city;

    @Column(name="country", nullable = false)
    private String country;


    @Column(name="region")
    private String region;

    @Column(name="longitude", nullable = false)
    private float longitude;

    @Column(name="latitude", nullable = false)
    private float latitude;

    @Column(name="temperature", nullable = false)
    private float temperature;

    @Column(name="pressure", nullable = false)
    private float pressure;

    @Column(name="humidity", nullable = false)
    private float humidity;

    @Column(name="wind_direction")
    private String wind_direction;

    @Column(name="wind_speed", nullable = false)
    private float wind_speed;


    public Location() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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


    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }
}