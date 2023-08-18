package AdditionalTest;

import Options.Location;
import Utilities.TestingHibernateUtilitiesTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testing.connections.HibernateUtilTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddtionalLocationTest extends TestingHibernateUtilitiesTest {

    private AdditionalTest additionalTest;

    @BeforeEach
    public void openSession() {

        System.out.println("Session created:" + super.session );
    }

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed:" + super.session );
    }

    @Test
    public void testValidLocation() throws SQLException {

        Location location = new Location("Tallinn", "Harju", "Estonia");

        assertEquals("Tallinn", location.getCity());
        assertEquals("Harju", location.getRegion());
        assertEquals("Estonia", location.getCountry());

        // assertNotNull( super.session, "Session is not initialized!" );

    }/*

    @Test
    public void testGetAllLocations() throws SQLException {
        List<Location> locations = locationDAO.getAll();
        assertNotNull(locations);
        assertTrue(locations.size() > 0);

    }*/
}
