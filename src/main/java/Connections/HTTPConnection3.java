package Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection3 {
    private static final String API_KEY = "v6A6CZaVIklQXx0f9sIvVFUYUr4ePcSq";
    private static final String LOCATIONS_API_URL = "http://dataservice.accuweather.com/locations/v1/cities/search";
    private static final String FORECAST_API_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/5day";

    public static StringBuilder getLocationRequest(String city) throws Exception {
        String url = LOCATIONS_API_URL + "?apikey=" + API_KEY + "&q=" + city;
        return getRequest(url);
    }

    public static StringBuilder getForecastRequest(String locationKey, String date) throws Exception {
        String url = FORECAST_API_URL + "/" + locationKey + "?apikey=" + API_KEY + "&date=" + date;
        return getRequest(url);
    }

    private static StringBuilder getRequest(String url) throws Exception {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}

//    URL url = new URL("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=" + apiKey + "&q=" + location);