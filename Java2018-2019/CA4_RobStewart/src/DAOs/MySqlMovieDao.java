package DAOs;

/**
 *
 * @author Rob
 */
import DTOs.Movie;
import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class MySqlMovieDao extends MySqlDao implements MovieDaoInterface {
    

    /**
     * Connects to the database with a prepared statement
     * Receives a result set from the database and creates a list of objects
     * List of Objects is then returned to the server
     * @return ArrayList of Movies
     * @throws DaoException 
     */
    @Override
    public ArrayList<Movie> findAllMovies() throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<>();
        
        try{
            con = this.getConnection();
            
            String query = "SELECT * FROM movies";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
                while(rs.next()){
                    Movie m = createMovieObject(rs);
                    movies.add(m);
            }
            
        }catch (SQLException e){
            throw new DaoException("findAllMovies() " + e.getMessage());
        }
        finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if (con != null){
                    freeConnection(con);
                }
            }catch (SQLException e){
                throw new DaoException("findAllMovies() " + e.getMessage());
            }
        }
     return movies;
    }
   /**
     * Connects to the database with a prepared statement using method arguments
     * Receives a result set from the database and creates a list of objects
     * List of Objects is then returned to the server
     * @return ArrayList of Movies
     * @throws DaoException 
     */
    @Override
    public ArrayList<Movie> findMovieById(int id) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<>();
        
        try{
            con = this.getConnection();
            
            String query = "SELECT * FROM movies WHERE movie_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
                if (rs.next()){
                   Movie m = createMovieObject(rs);
                   movies.add(m);
            }
        }catch (SQLException e){
            throw new DaoException("findMovieById() " + e.getMessage());
        }
        finally{
            try{
                if (rs != null){
                  rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (con != null){
                    freeConnection(con);
                }
            }catch (SQLException e){
                throw new DaoException("findMovieById() " + e.getMessage());
            }
        }
        return movies;
    }
    /**
     * Connects to the database with a prepared statement using method arguments
     * Receives a result set from the database and creates a list of objects
     * List of Objects is then returned to the server
     * @return ArrayList of Movies
     * @throws DaoException 
     */
    @Override
    public ArrayList<Movie> findMovieByTitle(String title) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<>();
        title = "%" + title + "%";
        
        try{
            con = this.getConnection();
            
            String query = "SELECT * FROM movies WHERE title LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();
                while (rs.next()){
                    Movie m = createMovieObject(rs);
                    movies.add(m);
            }
        }catch (SQLException e){
            throw new DaoException("findMovieByTitle() " + e.getMessage());
        }
        finally{
            try{
                if (rs != null){
                  rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (con != null){
                    freeConnection(con);
                }
            }catch (SQLException e){
                throw new DaoException("findMovieByTitle() " + e.getMessage());
            }
        }
        return movies;
     
    }
    /**
     * Connects to the database with a prepared statement using method arguments
     * Receives a result set from the database and creates a list of objects
     * List of Objects is then returned to the server
     * @return ArrayList of Movies
     * @throws DaoException 
     */
    @Override
    public ArrayList<Movie> findMovieByDirector(String director) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<>();
        director = "%" + director + "%";
        
        try{
            con = this.getConnection();
            
            String query = "SELECT * FROM movies WHERE director LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, director);
            rs = ps.executeQuery();
                while (rs.next()){
                    Movie m = createMovieObject(rs);
                    movies.add(m);
            }
        }catch (SQLException e){
            throw new DaoException("findMovieByDirector() " + e.getMessage());
        }
        finally{
            try{
                if (rs != null){
                  rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (con != null){
                    freeConnection(con);
                }
            }catch (SQLException e){
                throw new DaoException("findMovieByDirector() " + e.getMessage());
            }
        }
        return movies;
     
    }
    /**
     * Connects to the database with a prepared statement using method arguments
     * Receives a result set from the database and creates a list of objects
     * List of Objects is then returned to the server
     * @return ArrayList of Movies
     * @throws DaoException 
     */
    @Override 
    public ArrayList<Movie> findMovieByGenre (String g) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<>();
        g =  "%" + g + "%";
        try{
            con = this.getConnection();
            
            String query = "SELECT * FROM movies WHERE genre LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, g);
            rs = ps.executeQuery();
                while(rs.next()){
                    Movie m = createMovieObject(rs);
                    movies.add(m);
            }
        }catch (SQLException e){
            throw new DaoException("findMovieByGenre() " + e.getMessage());
        }
        finally{
            try{
                if (rs != null){
                  rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (con != null){
                    freeConnection(con);
                }
            }catch (SQLException e){
                throw new DaoException("findMovieByGenre() " + e.getMessage());
            }
        }  
        return movies;
    }
    /**
     * Updates the Database with a new Movie
     * All parameters are user input from a client
     * @param title
     * @param genre
     * @param director
     * @param runtime
     * @param plot
     * @param location
     * @param poster
     * @param rating
     * @param format
     * @param year
     * @param starring
     * @param copies
     * @param barcode
     * @param user_rating
     * @return added movie if successful
     * @throws DaoException 
     */
    @Override
    public ArrayList<Movie> addMovie(String title, String genre, String director, String runtime, String plot, String location, boolean poster, String rating, String format, int year, String starring, int copies, String barcode, double user_rating) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<>();
        
        try{
            con = this.getConnection();
            
            String query = "INSERT INTO movies (title, genre, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating)"
                         + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, genre);
            ps.setString(3, director);
            ps.setString(4, runtime);
            ps.setString(5, plot);
            ps.setString(6, location);
            ps.setBoolean(7, poster);
            ps.setString(8, rating);
            ps.setString(9, format);
            ps.setInt(10, year);
            ps.setString(11, starring);
            ps.setInt(12, copies);
            ps.setString(13, barcode);
            ps.setDouble(14, user_rating);
            ps.executeUpdate();
          
            query = "SELECT * FROM movies WHERE title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();
                if (rs.next()){
                    Movie m = createMovieObject(rs);
                    movies.add(m);
            }
        }catch (SQLException e){
            throw new DaoException("addMovie() " + e.getMessage());
        }
        finally{
            try{
                if (rs != null){
                  rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (con != null){
                    freeConnection(con);
                }
            }catch (SQLException e){
                throw new DaoException("addMovie() " + e.getMessage());
            }
        }
        return movies;
    }
    /**
     * Updates database by deleting movie by its id
     * Id is supplied by the client
     * @param id
     * @return empty arrayList if operation was successful 
     * @throws DaoException 
     */
    @Override
    public ArrayList<Movie> deleteMovieById(int id) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<>();
        
        try{
            con = this.getConnection();
            
            String query = "DELETE FROM movies WHERE movie_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
                
            query = "SELECT * FROM movies WHERE movie_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
                if (rs.next()){
                    Movie m = createMovieObject(rs);
                    movies.add(m);
            }
        }catch (SQLException e){
            throw new DaoException("deleteMovieById() " + e.getMessage());
        }
        finally{
            try{
                if (rs != null){
                  rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (con != null){
                    freeConnection(con);
                }
            }catch (SQLException e){
                throw new DaoException("deleteMovieById() " + e.getMessage());
            }
        }
        return movies;
    }
    /**
     * Updates a movie in the database by its id with a new title
     * Arguments are supplied by the client
     * @param id
     * @param title
     * @return updated movie on success
     * @throws DaoException 
     */
    @Override
    public ArrayList<Movie> updateMovieTitleById (int id, String title) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<>();
        
        try{
            con = this.getConnection();
            
            String query = "UPDATE movies SET title = ? WHERE movie_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setInt(2, id);
            ps.executeUpdate();
                
            query = "SELECT * FROM movies WHERE movie_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
                if (rs.next()){
                   Movie m = createMovieObject(rs);
                   movies.add(m);
            }
        }catch (SQLException e){
            throw new DaoException("updateMovieTitleById() " + e.getMessage());
        }
        finally{
            try{
                if (rs != null){
                  rs.close();
                }
                if (ps != null){
                    ps.close();
                }
                if (con != null){
                    freeConnection(con);
                }
            }catch (SQLException e){
                throw new DaoException("updateMovieTitleById() " + e.getMessage());
            }
        }
        return movies;
    }
    /**
     * Creates a movie object from the database result set
     * @param rs
     * @return Movie object
     * @throws SQLException 
     */
    public static Movie createMovieObject(ResultSet rs) throws SQLException{
         
                int movie_id = rs.getInt("movie_id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String director = rs.getString("director");
                String runtime = rs.getString("runtime");
                String plot = rs.getString("plot");
                plot = plot.replace('"', '\'');
                plot = plot.trim();
                String location = rs.getString("location");
                boolean poster = rs.getBoolean("poster");
                String rating = rs.getString("rating");
                String format = rs.getString("format");
                int year = rs.getInt("year");
                String starring = rs.getString("starring");
                int copies = rs.getInt("copies");
                String barcode = rs.getString("barcode");
                String user_ratings = rs.getString("user_rating");
                double user_rating = 0.0;

                    if (user_ratings != null && !user_ratings.equalsIgnoreCase("null") && !user_ratings.equalsIgnoreCase("n/a")){
                        user_rating = Double.parseDouble(user_ratings);
                    }
                ArrayList<String> genreList = new ArrayList<>(Arrays.asList(genre.split(","))); // splits the string of genres into an array of genres and puts them into a new Arraylist of genres
                    for (String s : genreList){
                        s.trim();
                    }
                return new Movie (movie_id, title, genreList, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
    }
}
