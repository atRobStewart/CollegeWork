package DTOs;
/**
 *
 * @author Rob
 */
import java.util.Date;

public class MovieUserWatched {
    private String user_id;
    private int movie_id;
    private Date timestamp;
    
    public MovieUserWatched(){};

    public MovieUserWatched(String user_id, int movie_id, Date timestamp) {
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MovieUserWatched{" + "user_id=" + user_id + ", movie_id=" + movie_id + ", timestamp=" + timestamp + '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
