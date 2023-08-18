package Options;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DisplayLocations {
    public static void displayTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("weatherlady");
        EntityManager em = emf.createEntityManager();

        // Selects all Location objects from the database and assigns it to a TypedQuery object named query.
        TypedQuery<Location> query = em.createQuery("SELECT l FROM Options.Location l", Location.class);

        // Executes the JPA query and assigns the result to a List
        List<Location> locations = query.getResultList();

        // Define the format specifier for each column
        String idFormat = "%-10s";
        String cityFormat = "%-30s";
        String countryFormat = "%-20s";
        String regionFormat = "%-20s";
        String latitudeFormat = "%-15s";
        String longitudeFormat = "%-15s";

        // Print the header row
        System.out.println(String.format(idFormat + cityFormat + countryFormat + regionFormat + latitudeFormat + longitudeFormat, "id", "cityName", "countryName", "region", "latitude", "longitude"));

        // Print each location row
        for (Location location : locations) {
            System.out.println(String.format(idFormat + cityFormat + countryFormat + regionFormat + latitudeFormat + longitudeFormat,
                    location.getId(), location.getCity(), location.getCountry(), location.getRegion(), location.getLatitude(), location.getLongitude()));
        }



        // Define the file paths
        String jsonFilePath = "C:/Users/RannastA/Documents/GitHub/PracticalProject/Weatherladyapp/locations.json";
        String txtFilePath = "C:/Users/RannastA/Documents/GitHub/PracticalProject/Weatherladyapp/locations.txt";

        try (PrintWriter txtWriter = new PrintWriter(new FileWriter(txtFilePath));
             FileWriter jsonWriter = new FileWriter(jsonFilePath)) {
            // Print the header row to the text file
            txtWriter.printf(idFormat + cityFormat + countryFormat + regionFormat + latitudeFormat + longitudeFormat + "%n", "id", "cityName", "countryName", "region", "latitude", "longitude");

            // Print each location row to the text file
            for (Location location : locations) {
                txtWriter.printf(idFormat + cityFormat + countryFormat + regionFormat + latitudeFormat + longitudeFormat + "%n",
                        location.getId(), location.getCity(), location.getCountry(), location.getRegion(), location.getLatitude(), location.getLongitude());
            }

            System.out.println("Data exported to TXT file successfully.");

            // Convert locations list to JSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(locations);

            // Write the JSON string to the JSON file
            jsonWriter.write(json);
            System.out.println("Data exported to JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        em.close();
        emf.close();
    }
}


