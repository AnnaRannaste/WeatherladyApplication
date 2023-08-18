package Options;

import Connections.HTTPConnection2;
import Connections.HTTPConnection1;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRequest {
    public static void addLocation(String typeCity) throws Exception {

        EntityManagerFactory emf = null;
        EntityManager em = null;

        Location location = new Location();


        // Extract values from the response1

        //String response1 = String.valueOf(ConnectionTest.getWeatherRequest(typeCity, dateStr));
        String response1 = String.valueOf(HTTPConnection1.getWeatherRequest(typeCity)); //HHTPConnection 1

        ObjectMapper mapper1 = new ObjectMapper(); //class is used to parse this string into a JsonNode object jsonNode
        JsonNode jsonNode1 = mapper1.readTree(response1); // navigates the JsonNode tree to extract values

    //    JsonNode locationNode = jsonNode1.get("location");
     //   if (locationNode != null) {

            String city = jsonNode1.get("location").get("name").asText();
            String country = jsonNode1.get("location").get("country").asText();
            String region = jsonNode1.get("location").get("region").asText();
            float temperature1 = jsonNode1.get("current").get("temperature").floatValue();
            float pressure1 = jsonNode1.get("current").get("pressure").floatValue();
            float humidity1 = jsonNode1.get("current").get("humidity").floatValue();
            String windDir = jsonNode1.get("current").get("wind_dir").asText();
            float windSpeed1 = jsonNode1.get("current").get("wind_speed").floatValue();


            // Extract values from the response2

            String response2 = String.valueOf(HTTPConnection2.getWeatherRequest(typeCity)); //HTTPConnection 2

            ObjectMapper mapper2 = new ObjectMapper();
            JsonNode jsonNode2 = mapper2.readTree(response2);
            float latitude = jsonNode2.get("coord").get("lat").floatValue();
            float longitude = jsonNode2.get("coord").get("lon").floatValue();
            float pressure2 = jsonNode2.get("main").get("pressure").floatValue();
            float humidity2 = jsonNode2.get("main").get("humidity").floatValue();
            float windSpeed2 = jsonNode2.get("wind").get("speed").floatValue();
            float temperature2 = jsonNode2.get("main").get("temp").floatValue();


            //  long unixTimestamp = 1620050400; // replace with your Unix timestamp
            long unixTimestamp = jsonNode2.get("dt").asLong();
            Date rawDate = new Date(unixTimestamp * 1000L); // Convert Unix timestamp to Java Date object
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(rawDate); // Convert Date object to formatted date string


            //Average calculation
            float temp = (temperature1 + temperature2) / 2;
            float pressure = (pressure1 + pressure2) / 2;
            float humidity = (humidity1 + humidity2) / 2;
            float windSpeed = (windSpeed1 + windSpeed2) / 2;


            // Save retrieved data to the database
            try {
                emf = Persistence.createEntityManagerFactory("weatherlady");
                em = emf.createEntityManager();
                em.getTransaction().begin();

                location.setCity(city);
                location.setDate(date);
                location.setLongitude(longitude);
                location.setLatitude(latitude);
                location.setRegion(region);
                location.setCountry(country);
                location.setTemperature(temp);
                location.setPressure(pressure);
                location.setHumidity(humidity);
                location.setWind_direction(windDir);
                location.setWind_speed(windSpeed);

                em.persist(location);
                em.getTransaction().commit();

                System.out.println("Location added successfully!");

                System.out.println("response from connection1: " + response1);

                System.out.println("________________________________________________________________________________");
                System.out.println("response from connection2: " + response2);


                em.persist(location);
                em.getTransaction().commit();


            } catch (Exception e) {

            } finally {
                if (em != null) {
                    em.close();
                }
                if (emf != null) {
                    emf.close();
                }
            }
        }
    }



