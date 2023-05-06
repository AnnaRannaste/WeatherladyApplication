package Options;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

public class SearchByDate {
    public static void displayHistoricalData(String sqlStartDate, String sqlEndDate) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("weatherlady");
        EntityManager em = emf.createEntityManager();



       // java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
       // java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        TypedQuery<Location> query = em.createQuery("SELECT l FROM Location l WHERE l.date BETWEEN :startDate AND :endDate", Location.class);
        query.setParameter("startDate", sqlStartDate);
        query.setParameter("endDate", sqlEndDate);

      //  query.setParameter("startDate", sqlStartDate, TemporalType.TIMESTAMP);
      //  query.setParameter("endDate", sqlEndDate, TemporalType.TIMESTAMP);
        List<Location> locations = query.getResultList();



        String idFormat = "%-10s";
        String cityFormat = "%-30s";
        String countryFormat = "%-20s";
        String regionFormat = "%-20s";
        String temperatureFormat = "%-15s";

        System.out.println(String.format(idFormat + cityFormat + countryFormat + regionFormat + temperatureFormat, "id", "cityName", "countryName", "region", "averageTemperature"));

        for (Location location : locations) {
            System.out.println(String.format(idFormat + cityFormat + countryFormat + regionFormat +
                    location.getId(), location.getCity(), location.getCountry(), location.getRegion(), location.getLatitude(), location.getLongitude()));
        }


        em.close();
        emf.close();
    }

}
