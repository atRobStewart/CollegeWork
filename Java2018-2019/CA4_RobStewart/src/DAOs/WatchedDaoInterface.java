package DAOs;

import Exceptions.DaoException;
import java.sql.Timestamp;

/**
 *
 * @author Rob
 */
public interface WatchedDaoInterface {
    
     public String findMoviesWatchedByUsername(String username) throws DaoException;
     public String watchMovieById (int movie_id , String username, Timestamp ts) throws DaoException;
     public String deleteAllMoviesById(int movie_id) throws DaoException;
}
