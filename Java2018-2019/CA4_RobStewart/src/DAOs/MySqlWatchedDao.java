package DAOs;

import Exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Rob
 */
public class MySqlWatchedDao extends MySqlDao implements WatchedDaoInterface{
    /**
     * Finds movie ids of watched movies by username
     * @param username
     * @return String of movie ids separated by comma
     * @throws DaoException 
     */
    public String findMoviesWatchedByUsername(String username) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String movieString = "";
        
        try{
            con = this.getConnection();
            
            String query = "SELECT * FROM watched WHERE user_id = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
                while(rs.next()){
                    movieString += rs.getInt("movie_id") + ",";
            }
                movieString = movieString.substring(0, movieString.length() -1);
                
        }catch (SQLException e){
            throw new DaoException("Recommendations - findByUser " + e.getMessage());
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
                throw new DaoException("Recommendations - findByUser " + e.getMessage());
            }
        }
     return movieString;
    }
    /**
     * Adds an entry to the watched table 
     * @param movie_id id of the movie that was watched
     * @param username client username
     * @param ts is local date and timestamp of when movie was watched
     * @return message success/failure
     * @throws DaoException 
     */
    public String watchMovieById (int movie_id , String username, Timestamp ts) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean hasResult = false;
        
        try{
            con = this.getConnection();
            
            String query = "INSERT INTO watched (user_id, movie_id, timestamp)"
                    + "VALUES (?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setInt(2, movie_id);
            ps.setTimestamp(3, ts);
            ps.executeUpdate();
         
            query = "SELECT * FROM watched WHERE user_id = ? AND timestamp = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setTimestamp(2, ts);
            rs = ps.executeQuery();
                if (rs.next()){
                    hasResult = true;
            }       
        }catch (SQLException e){
            throw new DaoException("watchMovieByid " + e.getMessage());
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
                throw new DaoException("watchMovieById " + e.getMessage());
            }
        }
     if (!hasResult){
            return messageSuccessFailure(false);
        }else{
            return messageSuccessFailure(true);
        }
    }
    /**
     * Deletes movie from watched table when that movie is deleted from the movies table
     * @param movie_id
     * @return message success/failure
     * @throws DaoException 
     */
    @Override
    public String deleteAllMoviesById (int movie_id) throws DaoException{
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean hasResult = false;
        
        try{
            con = this.getConnection();
            
            String query = "DELETE FROM watched WHERE movie_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, movie_id);
            ps.executeUpdate();
         
            query = "SELECT * FROM watched WHERE movie_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, movie_id);
            rs = ps.executeQuery();
                if (rs.next()){
                    hasResult = true;
            }       
        }catch (SQLException e){
            throw new DaoException("watchMovieByid " + e.getMessage());
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
                throw new DaoException("watchMovieById " + e.getMessage());
            }
        }
     if (!hasResult){
            return messageSuccessFailure(true);
        }else{
            return messageSuccessFailure(false);
        }
    }
    /**
      * Simple Json message to represent the success or failure of a command/query
      * @param b boolean representing success or failure
      * @return jsonString
      */
     public static String messageSuccessFailure(boolean b){
        String jsonString = "";
            if (!b){
                jsonString += "{\"message\": \"failure\",";
            }else{
                jsonString += "{\"message\": \"success\",";
                }
        jsonString += "\"length\": \"0\",";
        jsonString += "\"movies\": ["; 
        jsonString += "]}";
        System.out.println(jsonString);
    return jsonString;
    }
}
