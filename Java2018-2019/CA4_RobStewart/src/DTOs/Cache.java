package DTOs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Rob
 */
public class Cache {
    /**
     * HashMap representing the Cache
     * String is the Key and the command sent to the server
     * ArrayList<Movie> is the value returned from the DAO
     * ArraList<Movie> was needed over Movie alone because multiple DAO operations return multiple Movies, Movie will only work on single returns like findMovieById()
     */
    Map <String, ArrayList<Movie>> cache = new HashMap<>();
    
    /**
     * Data retrieved from the cache if the key is found
     * The key is the command sent to the server
     * Otherwise it returns null, meaning the Database needs to be accessed for data
     * @param key
     * @return 
     */
    public ArrayList<Movie> retrieveFromCache(String key){
        ArrayList<Movie> m = null;
        if (cache.containsKey(key)){
            m = cache.get(key);
        }
        return m;
    }
    /**
     * Adds the data with its server command to the cache
     * @param key
     * @param value 
     */
    public void addToCache(String key, ArrayList<Movie> value){
        cache.put(key, value);
    }
    /**
     * Clears the cache of data, used when any updates are made to the database
     */
    public void clearCache(){
        cache.clear();
    }
    /**
     * Currently unused, was needed for cached recommendations 
     * @param key 
     */
    public void removeFromCache(String key){
        cache.remove(key);
    }
}
