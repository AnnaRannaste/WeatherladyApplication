package MainEntry;

import Connections.HTTPConnection1;
import Options.AddRequest;
import Options.CurrentWeather;
import Options.DisplayLocations;
import Options.SearchByDate;


import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {


            Scanner scanner = new Scanner(System.in);


            System.out.println("1- Add location");
            System.out.println("2- List previous searches");
            System.out.println("3- Current forecast");
            System.out.println("4- Additional option - SearchByDate");
            System.out.println("5- Exit");


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

                    System.out.println("Choose an option:");
                    System.out.println("1. Enter city");
                    System.out.println("or");
                    System.out.println("2. Enter longitude");

                    int locationOption = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (locationOption == 1) {
                        System.out.println("Enter city: ");
                        String inputCity = scanner.nextLine();

                        AddRequest add = new AddRequest();
                        add.addLocation(inputCity);

                    } else if (locationOption == 2) {
                        System.out.println("Enter longitude: ");
                        String inputLongitude = scanner.nextLine();

                        AddRequest add1 = new AddRequest();
                        add1.addLocation(inputLongitude);
                    } else {
                        System.out.println("Invalid location option");
                    }
                    break;







//                    double longitude;
//                    // Prompt user for city
//
//                    Scanner scanner1 = new Scanner(System.in);
//                    System.out.println("Enter city: ");
//                    String inputCity = scanner1.nextLine();
//
//                    //Date
//                 //   Scanner scanner2 = new Scanner(System.in);
//                 //   System.out.println("Enter date: ");
//                //    String dateStr = scanner2.nextLine();
//
//                    AddRequest add = new AddRequest();
//                    add.addLocation(inputCity);
//
//                //    add.addLocation("inputCity", "dateStr");
//                    break;




                case 2:
                    DisplayLocations table = new DisplayLocations();
                    table.displayTable();
                    break;
                case 3:
                    System.out.println("Enter city:");
                    Scanner scn2 = new Scanner(System.in);

                    String enterCity = scn2.nextLine();

                 //   System.out.println("http://dataservice.accuweather.com/forecasts/v1/daily/5day/" + locationKey + "?apikey=" + apiKey);

                    String jsonString = String.valueOf(HTTPConnection1.getWeatherRequest(enterCity));
                    CurrentWeather forecast = new CurrentWeather();
                    forecast.currentForecast(jsonString);

//                    System.out.println("Enter city:");
//
//                    Scanner scanner3 = new java.util.Scanner(System.in);
//                    String city = scanner3.nextLine();
//
//
//                    System.out.println("Enter date (YYYY-MM-DD):");
//
//                    String date = scanner3.nextLine();
//
//                    // Look up the location key for the specified city
//                    String locationKey = null;
//                    try {
//                        StringBuilder locationResponse = HTTPConnection3.getLocationRequest(city);
//                        JSONArray locationJsonArray = new JSONArray(locationResponse.toString());
//                        locationKey = locationJsonArray.getJSONObject(0).getString("Key");
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                        return;
//                    }
//
//                    try {
//                        StringBuilder forecastResponse = HTTPConnection3.getForecastRequest(locationKey, date);
//                        System.out.println(forecastResponse.toString());
//                    } catch (Exception e) {
//                        System.out.println("Error: " + e.getMessage());
//                    }
//

                    break;


                case 4:
                //    String startDateString = "2023-05-01 17:05:54";
                //    String endDateString = "2023-05-02 20:20:49";

                    String sqlStartDate = "2023-05-03";
                    String sqlEndDate = "2023-05-05";

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
               //     Date startDate = sdf.parse(startDateString);
              //      Date endDate = sdf.parse(endDateString);
                    SearchByDate search = new SearchByDate();
                    search.displayHistoricalData(sqlStartDate, sqlEndDate);
                   // search.displayTable(startDate, endDate);
                    break;

                case 5:
                    System.out.println("Performing exit..Good bye");
                    scanner.close();
                    break;

            }
        }

    }

