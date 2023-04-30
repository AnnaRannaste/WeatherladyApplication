package Locations;

import jakarta.persistence.*;

public class Implementation {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("weatherlady");

    public static void addRequest(String city, String country, double longitude, double latitude, double temperature, double pressure, double humidity, double wind_speed) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Location loc = new Location();
            loc.setCity(city);
            loc.setCountry(country);
          //  loc.setRegion(region);
            loc.setLongitude(longitude);
            loc.setLatitude(latitude);
            loc.setTemperature(temperature);
            loc.setPressure(pressure);
            loc.setHumidity(humidity);
          //  loc.setWind_direction(wind_direction);
            loc.setWind_speed(wind_speed);


            em.persist(loc);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();

        }

    }

}