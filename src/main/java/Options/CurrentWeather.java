package Options;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CurrentWeather {
    public static void currentForecast(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);

            String city = jsonNode.get("location").get("name").asText();
            String country = jsonNode.get("location").get("country").asText();
            int temperature = jsonNode.get("current").get("temperature").asInt();
            int pressure = jsonNode.get("current").get("pressure").asInt();
            int humidity = jsonNode.get("current").get("humidity").asInt();
            String windDir = jsonNode.get("current").get("wind_dir").asText();
            int windSpeed = jsonNode.get("current").get("wind_speed").asInt();

            System.out.println(city + ", " + country + ":");
            System.out.println("Current temperature: " + temperature + "°C");
            System.out.println("Pressure: " + pressure + "hPa");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind: " + + windSpeed + " m/s (" + windDir + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
