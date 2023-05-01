package Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection2 {

    public static StringBuffer getWeatherRequest(String location) throws Exception {
        //public static StringBuffer getWeatherRequest(float lon, float lat) throws Exception {

        String apiKey = "d4f7dccdedfd19f73a866ae1ed8f86a1";

        // Construct the API request URL
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=" + apiKey);

        // Send the API request
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        // Read the API response
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();

        return response;
    }
}
