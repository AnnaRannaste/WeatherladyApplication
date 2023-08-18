//package json;
//
//import Connections.HTTPConnection1;
//import Options.AddRequest;
//import Options.Location;
//import com.google.gson.Gson;
//import org.apache.logging.log4j.core.config.Order;
//import org.json.JSONException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import top.jfunc.json.impl.JSONObject;
//
//import java.util.List;
//import java.sql.SQLException;
//import java.util.regex.Pattern;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//public class ParseJSONTest {
//    private HTTPConnection1 httpWeather = null;
//    private final AddRequest locationRepository = new AddRequest();
//
//    @BeforeAll
//    public static void beforeAllSetupClass() {
//
//    }
//
//    @Order(1)
//    @org.testng.annotations.Test
//    public void testAddNewLocation() throws SQLException {
//
//        assertNotNull(locationRepository);
//
//        Assertions.assertThrows( SQLException.class, () -> {
//            locationRepository.addLocation( "New City", "Tanzania", "", 60.34d, 45.78d );
//
//        });
//
//    }
//
//    @Order(2)
//    @Test
//    public void testGetAllLocations() throws SQLException, JSONException {
//
//        List<Location> locations = locationRepository.getAll();
//
//        assertNotNull(locations);
//        assertTrue(locations.size() > 0);
//
//        locations.forEach( System.out::println );
//
//        final HTTPConnection1 httpWeather = new HTTPConnection1( AccessKey.one, locations.stream().findFirst().get().getCity() );
//
//        System.out.println( httpWeather.getJSON() );
//
//        // Reconstruct from string, check parsing rules by that
//        top.jfunc.json.impl.JSONObject asJSONObject =  (top.jfunc.json.impl.JSONObject)httpWeather.getJSON();
//
//        testGsonJsonPath( asJSONObject,"request");
//
//        testGsonJsonPath( asJSONObject, "location");
//
//        // Check for location digits
//        testDoubleGsonJsonPath( asJSONObject, "$.location.lon" );
//
//        testDoubleGsonJsonPath( asJSONObject, "$.location.lat" );
//
//    }
//
//    protected void testDoubleGsonJsonPath(final top.jfunc.json.impl.JSONObject jsonObject, final String jsonPath ) throws JSONException {
//
//        System.out.println(jsonObject.toString());
//
//        try {
//
//            // Convert for parsing, check for syntax
//            top.jfunc.json.impl.JSONObject forParsing = new JSONObject(jsonObject.toString());
//
//            String digit = JsonPath.read(jsonObject.toString(), jsonPath).toString();
//
//            Pattern pattern = Pattern.compile("\\d+\\.\\d{3}");
//            Assertions.assertTrue(pattern.matcher(digit).matches(), "Expected a digit convertible to Double");
//
//            System.out.println(String.format("%f is a converted double", Double.parseDouble(digit)));
//
//        } catch (PathNotFoundException pathException) {
//            fail("Unexpected exception: " + pathException.getMessage(), pathException );
//        }
//
//    }
//
//    protected void testGsonJsonPath( final top.jfunc.json.impl.JSONObject jsonObject, final String tagString ) {
//
//        //Assertions.assertNotNull(jsonObject.getByPath( tagString ));
//
//    }
//
//}