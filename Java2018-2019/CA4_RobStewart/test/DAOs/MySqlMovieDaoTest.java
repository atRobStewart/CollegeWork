/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Movie;
import java.sql.ResultSet;
import java.util.ArrayList;
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
public class MySqlMovieDaoTest {
    
    public MySqlMovieDaoTest() {
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
     * Test of findAllMovies method, of class MySqlMovieDao.
     */
    @Ignore
    public void testFindAllMovies() throws Exception {
        System.out.println("findAllMovies");
        MySqlMovieDao instance = new MySqlMovieDao();
        ArrayList<Movie> expResult = null;
        ArrayList<Movie> result = instance.findAllMovies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMovieById method, of class MySqlMovieDao.
     */
    @Test
    public void testFindMovieById() throws Exception {
        System.out.println("findMovieById");
        int id = 0;
        MySqlMovieDao instance = new MySqlMovieDao();
        ArrayList<Movie> expResult = new ArrayList<>();
        ArrayList<Movie> result = instance.findMovieById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        
        id = 1;
         ArrayList<String> g = new ArrayList<String>();
         g.add("test");
        expResult.add(new Movie(1, "test1", g, "test", "test", "test", "test", false,  "test", "test", 1970,  "test", 1,  "test", 0.0));
        result = instance.findMovieById(id);
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of findMovieByTitle method, of class MySqlMovieDao.
     */
    @Test
    public void testFindMovieByTitle() throws Exception {
        System.out.println("findMovieByTitle");
        String title = "test1";
        MySqlMovieDao instance = new MySqlMovieDao();
        ArrayList<Movie> expResult = new ArrayList<>();
        ArrayList<Movie> result = instance.findMovieByTitle(title);
        ArrayList<String> g = new ArrayList<String>();
        g.add("test");
        expResult.add(new Movie(1, "test1", g, "test", "test", "test", "test", false,  "test", "test", 1970,  "test", 1,  "test", 0.0));
//        System.out.println(expResult.toString());
//        System.out.println(result.toString());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findMovieByDirector method, of class MySqlMovieDao.
     */
    @Test
    public void testFindMovieByDirector() throws Exception {
        System.out.println("findMovieByDirector");
        String director = "test";
        MySqlMovieDao instance = new MySqlMovieDao();
        ArrayList<Movie> expResult = new ArrayList<>();
        ArrayList<Movie> result = instance.findMovieByDirector(director);
        ArrayList<String> g = new ArrayList<String>();
        g.add("test");
        expResult.add(new Movie(1, "test1", g, "test", "test", "test", "test", false,  "test", "test", 1970,  "test", 1,  "test", 0.0));
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findMovieByGenre method, of class MySqlMovieDao.
     */
    @Test
    public void testFindMovieByGenre() throws Exception {
        System.out.println("findMovieByGenre");
        String g = "test";
        MySqlMovieDao instance = new MySqlMovieDao();
        ArrayList<Movie> expResult = new ArrayList<>();
        ArrayList<Movie> result = instance.findMovieByGenre(g);
        ArrayList<String> gr = new ArrayList<String>();
        gr.add("test");
        expResult.add(new Movie(1, "test1", gr, "test", "test", "test", "test", false,  "test", "test", 1970,  "test", 1,  "test", 0.0));
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addMovie method, of class MySqlMovieDao.
     */
    @Ignore
    public void testAddMovie() throws Exception {
        System.out.println("addMovie");
        String title = "";
        String genre = "";
        String director = "";
        String runtime = "";
        String plot = "";
        String location = "";
        boolean poster = false;
        String rating = "";
        String format = "";
        int year = 0;
        String starring = "";
        int copies = 0;
        String barcode = "";
        double user_rating = 0.0;
        MySqlMovieDao instance = new MySqlMovieDao();
        ArrayList<Movie> expResult = null;
        ArrayList<Movie> result = instance.addMovie(title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMovieById method, of class MySqlMovieDao.
     */
    @Ignore
    public void testDeleteMovieById() throws Exception {
        System.out.println("deleteMovieById");
        int id = 0;
        MySqlMovieDao instance = new MySqlMovieDao();
        ArrayList<Movie> expResult = null;
        ArrayList<Movie> result = instance.deleteMovieById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMovieTitleById method, of class MySqlMovieDao.
     */
    @Ignore
    public void testUpdateMovieTitleById() throws Exception {
        System.out.println("updateMovieTitleById");
        int id = 0;
        String title = "";
        MySqlMovieDao instance = new MySqlMovieDao();
        ArrayList<Movie> expResult = null;
        ArrayList<Movie> result = instance.updateMovieTitleById(id, title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMovieObject method, of class MySqlMovieDao.
     */
    @Ignore
    public void testCreateMovieObject() throws Exception {
        System.out.println("createMovieObject");
        ResultSet rs = null;
        Movie expResult = null;
        Movie result = MySqlMovieDao.createMovieObject(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
