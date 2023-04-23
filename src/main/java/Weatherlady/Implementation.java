package Weatherlady;

import com.mysql.cj.Session;
import jakarta.persistence.*;
import org.hibernate.type.descriptor.java.UUIDJavaType;

import java.util.List;
import java.util.UUID;

public class Implementation {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("weatherlady");

    public static void main(String[] args) {

        addCity(1, "Tallinn", 24.753574, 59.436962, "Harjumaa", "Estonia"  );
        addCity(2, "ToUpdate", 13.404954, 52.520008, "Brandenburg", "Germany"  );
        addCity(3, "Berlin", 13.404954, 52.520008, "Brandenburg", "Germany"  );
      //  getCity(1);
       updateCity(2, "Bremen");
       deleteCity(3);
        emf.close();
    }

        public static void getCity(int id) {
            EntityManager em = emf.createEntityManager();
            String strQuery = "select * from locations";
            TypedQuery<Location> tq = em.createQuery(strQuery, Location.class);
            List<Location> locationsList;

            try {
                locationsList = tq.getResultList();
                locationsList.forEach(lctn -> System.out.println(lctn.getCity() + " " + lctn.getCountry()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                em.close();
            }

        }

    public static void addCity(int id, String city, double longitude, double latitude, String region, String country) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            Location loc = new Location();
            loc.setId(id);
            loc.setCity(city);
            loc.setLongitude(longitude);
            loc.setLatitude(latitude);
            loc.setRegion(region);
            loc.setCountry(country);

            em.persist(loc);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();

        }

    }

    public  static void updateCity(int id, String city){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;

        Location cty = null;

        try {
            et = em.getTransaction();
            et.begin();

            cty  = em.find(Location.class, id);
            cty.setCity(city);

            em.persist(cty);
            et.commit();
        } catch (Exception e) {
            if (et!=null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

    }
        public static void deleteCity(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;
        Location cty = null;

        try {
            et = em.getTransaction();
            et.begin();
            cty = em.find(Location.class, id);
            em.remove(cty);
            et.commit();
        } catch (Exception e) {
            if(et!=null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }


    }

   }

