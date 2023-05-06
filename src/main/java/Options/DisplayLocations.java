package Options;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.hibernate.boot.jaxb.hbm.transform.HbmXmlTransformer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DisplayLocations {
    public static void displayTable() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("weatherlady");
        EntityManager em = emf.createEntityManager();

        //selects all Location objects from the database and assigns it to a TypedQuery object named query.
        TypedQuery<Location> query = em.createQuery("SELECT l FROM Options.Location l", Location.class);

        //executes the JPA query and assigns the result to a List
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


  //      System.out.println(String.format("%-4s%-30s%-20s%-20s%-20s%-20s", "id", "cityName", "countryName", "region", "latitude", "longitude"));
//
 //       for (Location location : locations) {
 //          System.out.println(String.format("%-4s%-30s%-20s%-20s%-20s%-20s", location.getId(), location.getCity(), location.getCountry(), location.getRegion(), location.getLatitude(), location.getLongitude()));


        em.close();
        emf.close();
    }


//    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    String json = gson.toJson(locations);
//
//
//
//// Define the file path
//        String filePath = "path/to/your/file.json";
//
//        try (
//    FileWriter fileWriter = new FileWriter(filePath)) {
//        // Write the JSON string to the file
//        fileWriter.write(json);
//        System.out.println("Data exported to JSON file successfully.");
//        } catch (
//    IOException e) {
//        e.printStackTrace();
//        }
}