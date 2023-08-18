package Utilities;

import Options.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestingHibernateUtilitiesTest {

    private static SessionFactory sessionFactory;
    protected Session session;

    @BeforeAll
    public static void setup() throws SQLException, ClassNotFoundException, IOException {

        // Check whether such class can be loaded at all in runtime
        assertNotNull( Class.forName("org.hsqldb.jdbc.JDBCDriver") );
        sessionFactory = TestingHibernateUtilities.getSessionFactory(Location.class);

        assertNotNull( sessionFactory, "Session factory initialization fails" );
        assertFalse( sessionFactory.isClosed(), "Session cannot be opened" );
        System.out.println("SessionFactory created");
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @Test
    void getSessionFactory() {
    }


    @Test
    public void testCreate() {
        Transaction transaction = session.beginTransaction();
        Location testlocation = new Location("Tallinn", "Estonia", "Harjumaa");
        session.persist(testlocation);
        assertNotNull(testlocation.getId());
        transaction.commit();
    }


    @Test
    public void testGet() {
        Transaction transaction = session.beginTransaction();
        Location testlocation = new Location("Tallinn", "Estonia", "Harjumaa");
        session.persist(testlocation);
        transaction.commit();

        transaction = session.beginTransaction();
        Location retrievedLocation = session.get(Location.class, testlocation.getId());
        assertNotNull(retrievedLocation);
        assertEquals("Tallinn", retrievedLocation.getCity());
        assertEquals("Harjumaa", retrievedLocation.getRegion());
        assertEquals("Estonia", retrievedLocation.getCountry());
        transaction.commit();
    }


    @Test
    public void testList() {
        Transaction transaction = session.beginTransaction();
        session.persist(new Location("Tallinn", "Harjumaa", "Estonia"));
        session.persist(new Location("Oslo", "Norway", "Norway"));
        session.persist(new Location("Berlin", "Germany", "Germany"));
        transaction.commit();

        transaction = session.beginTransaction();
        List<Location> locations = session.createQuery("FROM Location").list();
        assertEquals(4, locations.size());
        transaction.commit();
    }

    @Test
    public void testUpdate() {
        Transaction transaction = session.beginTransaction();
        Location testlocation = new Location("Tallinn", "Estonia", "Harjumaa");
        session.persist(testlocation);
        transaction.commit();

        transaction = session.beginTransaction();
        testlocation.setRegion("Harju");
        session.merge(testlocation);
        transaction.commit();

        transaction = session.beginTransaction();
        Location updatedLocation = session.get(Location.class, testlocation.getId());
        assertEquals("Harju", updatedLocation.getRegion());
        transaction.commit();
    }


    @Test
    public void testDelete() {
        Transaction transaction = session.beginTransaction();
        Location testlocation = new Location("Riga", "Latvia", "Riga");
        session.persist(testlocation);
        transaction.commit();

        transaction = session.beginTransaction();
        session.delete(testlocation);
        transaction.commit();

        transaction = session.beginTransaction();
        Location deletedLocation = session.get(Location.class, testlocation.getId());
        assertNull(deletedLocation);
        transaction.commit();
    }

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }
}