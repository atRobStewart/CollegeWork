package BusinessObjects;

import DTOs.Movie;
import javax.json.JsonObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Rob
 */
public class ClientTest {
    
    public ClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Client.
     */
    @Ignore
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Client.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class Client.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        Client instance = new Client();
        instance.start();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of movieFromJson method, of class Client.
     */
    @Ignore
    public void testMovieFromJson() {
        System.out.println("movieFromJson");
        JsonObject rs = null;
        Movie expResult = null;
        Movie result = Client.movieFromJson(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of jsonFromString method, of class Client.
     */
    @Ignore
    public void testJsonFromString() {
        System.out.println("jsonFromString");
        String js = "";
        JsonObject expResult = null;
        JsonObject result = Client.jsonFromString(js);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
