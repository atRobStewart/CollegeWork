package DAOs;
/**
 *
 * @author Rob
 */
import DTOs.Movie;
import Exceptions.DaoException;
import java.util.ArrayList;

public interface MovieDaoInterface {
    public ArrayList<Movie> findAllMovies() throws DaoException;
    public ArrayList<Movie> findMovieByGenre(String g) throws DaoException;
    public ArrayList<Movie> findMovieByDirector(String director) throws DaoException;
    public ArrayList<Movie> findMovieById(int id) throws DaoException; 
    public ArrayList<Movie> findMovieByTitle(String t) throws DaoException;
    public ArrayList<Movie> addMovie (String title, String genre, String director, String runtime, String plot, String location, boolean poster, String rating, String format, int year, String starring, int copies, String barcode, double user_rating) throws DaoException;
    public ArrayList<Movie> deleteMovieById (int id) throws DaoException;
    public ArrayList<Movie> updateMovieTitleById (int id, String title) throws DaoException;
}
