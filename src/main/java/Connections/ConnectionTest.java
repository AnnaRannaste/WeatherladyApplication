package Connections;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ConnectionTest {

        public static StringBuffer getWeatherRequest(String location, String dateStr) throws Exception {

            String apiKey = "4282a84e00e15a0807a9ebd10b844afe";

            // Check if date is valid, otherwise use tomorrow's date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            URL url1 = null;
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format, using tomorrow's date instead");
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DATE, 1);
                date = c.getTime();
                url1 = new URL("http://api.weatherstack.com/forecast?access_key=" + apiKey + "&query=" + location + "&forecast_days=1");
            }
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(date);

            // Construct the API request URL
            URL url = new URL("http://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + location);

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

            // If a valid date was not provided, use the response from the second API request
            if (url1 != null) {
                HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
                conn1.setRequestMethod("GET");

                BufferedReader in1 = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
                String inputLine1;
                StringBuffer response1 = new StringBuffer();
                while ((inputLine1 = in1.readLine()) != null) {
                    response1.append(inputLine1);
                }
                in1.close();
                conn1.disconnect();

                return response1;
            }

            return response;
        }
    }


