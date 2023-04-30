package MainEntry;

import Locations.Implementation;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.println("1- Add location");
        System.out.println("2- List previous searches");
        System.out.println("3- current forecast ");
        System.out.println("4- Exit");

        System.out.println("Choose an action: ");

        int action;

        if (scanner.hasNextInt()) {
            action = scanner.nextInt();
        } else {
            System.out.println("Invalid action");
            return;
        }

        switch (action) {

            case 1:
                // Prompt user for city
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter city: ");
                String city = scanner1.nextLine();

                if (city.length() > 0) {


                    String apiKey = "d4f7dccdedfd19f73a866ae1ed8f86a1";

                    try {
                        // Construct the API request URL
                        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey);

                        // Send the API request
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");

                        // Read the API response
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String response = "";
                        String line;
                        while ((line = in.readLine()) != null) {
                            response += line;
                        }
                        in.close();
                        System.out.println(response);

                        // Parse the JSON response
                        JSONObject jsonObj = new JSONObject(response);


                        // Extract the country and region?? values from the response
                        JSONObject locationObj1 = jsonObj.getJSONObject("sys");
                        String country = locationObj1.getString("country");

                        // Extract the latitude and longitude values from the response
                        JSONObject locationObj2 = jsonObj.getJSONObject("coord");
                        double latitude = locationObj2.getDouble("lat");
                        double longitude = locationObj2.getDouble("lon");

                        // Extract the temperature, pressure and humidity values from the response
                        JSONObject locationObj3 = jsonObj.getJSONObject("main");
                        double temperature = locationObj3.getDouble("temp");
                        double pressure = locationObj3.getDouble("pressure");
                        double humidity = locationObj3.getDouble("humidity");


                        // Extract the wind_direction?? and speed values from the response
                        JSONObject locationObj4 = jsonObj.getJSONObject("wind");
                        //   String wind_direction = locationObj4.getString("");
                        double wind_speed = locationObj4.getDouble("speed");

                        //     System.out.println("Current city_name : " + city_name);
                        System.out.println("Current city : " + city);
                        System.out.println("current country: " + country);
                        System.out.println("latitude: " + latitude);
                        System.out.println("longitude: " + longitude);
                        System.out.println("temperature: " + temperature);
                        System.out.println("pressure: " + pressure);
                        System.out.println("humidity: " + humidity);
                        System.out.println("wind_speed: " + wind_speed);
                        // Use the latitude and longitude values in a subsequent API request
                        //     URL forecastUrl = new URL("https://api.openweathermap.org/data/2.5/forecast?lat=" + latitude + "&lon=" + longitude + "&lon=" + humidity + "&appid=" + apiKey);
                        // ...


                        Implementation insertRequest = new Implementation();
                        insertRequest.addRequest(city, country, longitude, latitude, temperature, pressure, humidity, wind_speed);


                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }


                } else {
                    System.out.println("Invalid data");
                }


                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                System.out.println("Performing exit");
                scanner.close();
                break;
        }
    }

}





