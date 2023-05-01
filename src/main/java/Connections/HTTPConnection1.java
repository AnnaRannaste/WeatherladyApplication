package Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection1 {

    public static StringBuffer getWeatherRequest(String location) throws Exception {

        String apiKey = "4282a84e00e15a0807a9ebd10b844afe";

        // Construct the API request URL
        URL url = new URL("http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + location);
     //   URL url1 = new URL("http://api.weatherstack.com/forecast?access_key=" + apiKey + "&query=" + location + "&forecast_days=1");

        // Send the API request
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Read the API response
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();

        return response;
    }
}


