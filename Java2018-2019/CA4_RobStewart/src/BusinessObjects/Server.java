package BusinessObjects;

import DAOs.MySqlMovieDao;
import DAOs.MovieDaoInterface;
import DAOs.MySqlWatchedDao;
import DAOs.WatchedDaoInterface;
import DTOs.Cache;
import DTOs.Movie;
import Exceptions.DaoException;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;


/**
 *
 * @author Rob
 */
public class Server {
    public static void main(String[] args){
        Server server = new Server();
        server.start();
    }
    public void start(){
        try{
            /**
             * Data Access Objects are all instantiated along with the Cache
             * ServerSocket is set up to listen for connections on port 8080
             * Logger is instantiated to record all actions
             * FileHandler opens the file that the logger writes to 
             * SimpleFormatter makes records easy to read
             * log.setUseParentHandlers(false) disables log comments on the server console
             */
            MovieDaoInterface IMovieDao = new MySqlMovieDao();
            WatchedDaoInterface IWatchedDao = new MySqlWatchedDao();
            Cache cache = new Cache();
            ServerSocket ss = new ServerSocket(8080);
            Logger log = Logger.getLogger("Server Action Logs");
            FileHandler fileHandling = new FileHandler("serverLog.txt", true);
            log.addHandler(fileHandling);
            SimpleFormatter format = new SimpleFormatter();
            fileHandling.setFormatter(format);
            log.setUseParentHandlers(false);

            System.out.println("Server: Server started. Listening for connections on port 8080...");
            log.info("Server started. Port 8080");
            
            // a number for clients that the server allocates as clients connect
            int clientNumber = 0;  
            
            /**
             * loop continuously to accept new client connections
             * listen (and wait) for a connection, accept the connection, and open a new socket to communicate with the client
             */
            while (true) {
                Socket socket = ss.accept();                    
                clientNumber++;
                
                System.out.println("Server: Client " + clientNumber + " has connected.");
                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                 // create a new ClientHandler for the clientwith all required object instances, and run it in its own thread
                Thread t = new Thread(new ClientHandler(socket, IMovieDao, IWatchedDao, clientNumber, cache, log));
                t.start();                                                
                
                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                log.info("ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        }catch(IOException e){
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    /**
     * Each ClientHandler communicates with one Client
     */
    public class ClientHandler implements Runnable{  
        // all necessary instance variables
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;
        MovieDaoInterface dao;
        WatchedDaoInterface wdao;
        Cache cache;
        Logger log;
        
        // ClientHandler constructor
        public ClientHandler(Socket clientSocket, MovieDaoInterface IMovieDao, WatchedDaoInterface IWatchedDao, int clientNumber, Cache c, Logger L){
            dao = IMovieDao;
            wdao = IWatchedDao;
            cache = c;
            log = L;
            try{
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);
                
                OutputStream os = clientSocket.getOutputStream();     
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer
                
                this.clientNumber = clientNumber;  // ID number that we are assigning to this client
                
                this.socket = clientSocket;  // store socket ref for closing 

            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        @Override
        public void run(){
            String message;
            try {
                // wait for client command
                while((message = socketReader.readLine()) != null){  
                    
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);
                    log.info("Read command from client " + clientNumber + ": " + message);
                    /**
                     * Server tries to retrieve data from the cache first
                     * If the cache doesn't have the required information, it then requests from the DAO
                     * The DAO gives back an arrayList of Movie Objects
                     * Data is then turned into jsonString format and sent to the client
                     * Data is then stored in the cache
                     * Cached data will be used in future queries until an update to the database is made, previous steps will be followed in this instance
                     */
                    if(message.startsWith("findAllMovies")){
                        log.info("try to retrieve from cache, line 145");
                        ArrayList<Movie> m = cache.retrieveFromCache(message); 
                            if (m == null){
                                log.info("dao.findAllMovies(), line 148");
                                ArrayList<Movie> resultList = dao.findAllMovies();  
                                String result = getJsonString(resultList);
                                socketWriter.println(result);  // sends current time to client as long int
                                log.info("cache.addToCache(), line 152");
                                cache.addToCache(message, resultList); 
                            }else{
                                String s = getJsonString(m);
                                socketWriter.println(s);
                            }
                    } 
                    /**
                     * Similar to findAllMovies
                     * message from the client is split at the tilde delimiter
                     * All string arguments received from the split are parsed and used in the DAO query
                     * 
                     * See findAllMovies if comments for in detail explanation of the code
                     */
                    else if(message.startsWith("findMovieById")){ 
                        log.info("try to retrieve from cache, line 167");
                        ArrayList<Movie> m = cache.retrieveFromCache(message);
                            if (m == null){
                            String[] commands = message.split("~");
                            int id = Integer.parseInt(commands[1]);
                            log.info("dao.findMovieById(), line 172");
                            ArrayList<Movie> resultList = dao.findMovieById(id);
                            String result = getJsonString(resultList);
                            socketWriter.println(result);
                            log.info("cache.addToCache(), line 176");
                            cache.addToCache(message, resultList);
                            }else{
                                String s = getJsonString(m);
                                socketWriter.println(s);
                            }
                    }
                    /**
                     * Similar to findAllMovies
                     * message from the client is split at the tilde delimiter
                     * All string arguments received from the split are parsed and used in the DAO query
                     * 
                     * See findAllMovies if comments for in detail explanation of the code
                     */
                    else if(message.startsWith("findMovieByTitle")){
                        log.info("try to retrieve from cache, line 191");
                         ArrayList<Movie> m = cache.retrieveFromCache(message);
                            if (m == null){
                            String[] commands = message.split("~");  
                            String title = (commands[1]);
                            log.info("dao.findMovieByTitle(), line 196");
                            ArrayList<Movie> resultList = dao.findMovieByTitle(title);
                            String result = getJsonString(resultList);
                            socketWriter.println(result);
                            log.info("cache.addToCache(), line 200");
                            cache.addToCache(message, resultList);
                            }else{
                                String s = getJsonString(m);
                                socketWriter.println(s);
                            }
                    }
                    /**
                     * Similar to findAllMovies
                     * message from the client is split at the tilde delimiter
                     * All string arguments received from the split are parsed and used in the DAO query
                     * 
                     * See findAllMovies if comments for in detail explanation of the code
                     */
                    else if(message.startsWith("findMovieByGenre")){
                        log.info("try to retrieve from cache, line 215");
                        ArrayList<Movie> m = cache.retrieveFromCache(message);
                            if (m == null){
                            String[] commands = message.split("~");  
                            String genre = (commands[1]);
                            log.info("dao.findMovieByGenre(), line 220");
                            ArrayList<Movie> resultList = dao.findMovieByGenre(genre);
                            String result = getJsonString(resultList);
                            socketWriter.println(result);
                            log.info("cache.addToCache(), line 224");
                            cache.addToCache(message, resultList);
                            }else{
                                String s = getJsonString(m);
                                socketWriter.println(s);
                            }
                    }
                    /**
                     * message from client is split and some string arguments are parsed and used in the DAO query
                     * returns either success or failure to the client
                     * on success the cache is cleared
                     */
                    else if(message.startsWith("addMovie")){
                        String[] commands = message.split("~");  
                        String titles = (commands[1]);
                        String genres = (commands[2]);
                        String director = (commands[3]);
                        String runtime = (commands[4]);
                        String plot = (commands[5]);
                        String location = (commands[6]);
                        boolean poster = Boolean.parseBoolean(commands[7]);
                        String rating = (commands[8]);
                        String format = (commands[9]);
                        int year = Integer.parseInt(commands[10]);
                        String starring = (commands[11]);
                        int copies = Integer.parseInt(commands[12]);
                        String barcode = (commands[13]);
                        double userRating = Double.parseDouble(commands[14]);
                        log.info("dao.addMovie(), line 252");
                        ArrayList<Movie> resultList = dao.addMovie(titles, genres, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, userRating);
                        String string = "";
                        if (resultList.isEmpty()){
                            string = messageSuccessFailure(false);
                        } else {
                            string = messageSuccessFailure(true);
                            log.info("clearcache(), line 259");
                            cache.clearCache();
                        }
                        socketWriter.println(string);
                    }
                    /**
                     * message from client is split and some string arguments are parsed and used in the DAO query
                     * returns either success or failure to the client
                     * on success the cache is cleared
                     */
                    else if(message.startsWith("deleteMovieById")){
                        String[] commands = message.split("~");
                        int id = Integer.parseInt(commands[1]);
                        log.info("dao.deleteMovieById(), line 272");
                        ArrayList<Movie> resultList = dao.deleteMovieById(id);
                        wdao.deleteAllMoviesById(id);
                        String string = "";
                        if (resultList.size() == 0){
                            string = messageSuccessFailure(true);
                            log.info("clearcache(), line 278");
                            cache.clearCache();
                        } else {
                            string = messageSuccessFailure(false);
                        }
                        socketWriter.println(string);
                    }
                    /**
                     * message from client is split and some string arguments are parsed and used in the DAO query
                     * returns either success or failure to the client
                     * on success the cache is cleared
                     */
                    else if(message.startsWith("updateMovieTitleById")){
                        String[] commands = message.split("~");
                        int id = Integer.parseInt(commands[1]);
                        String title = (commands[2]);
                        log.info("dao.updateMovieTitleById(), line 294");
                        ArrayList<Movie> resultList = dao.updateMovieTitleById(id, title);
                        String string = "";
                        if (resultList.isEmpty()){
                            string = messageSuccessFailure(false);
                        } else {
                            string = getJsonString(resultList);
                            log.info("clearcache(), line 301");
                            cache.clearCache();
                        }
                        socketWriter.println(string);
                    }
                    /**
                     * Retrieves a list of movie ids from the watched movies table
                     * Creates an arrayList of watched movies and counts the frequency of each genre over all movies
                     * Selects the genre with the highest frequency and selects movies with that genre from the movie table
                     * Removes all watched movies from the new movie by genre list
                     * Movies are then randomly removed until only 10 are left
                     * Data is then converted to jsonString and sent to the client
                     * Cache is left out because the same movies would be sent until an update clears the cache
                     */
                    else if (message.startsWith("recommended")){
                        log.info("try to retrieve from cache, line 316");
                        String[] commands = message.split("~");
                            String username = (commands[1]);
                            log.info("wdao.findMoviesWatchedByUsername(), line 319" + username);
                            String idString = wdao.findMoviesWatchedByUsername(username);
                            String[] ids = idString.split(",");
                            ArrayList<Movie> movie = new ArrayList<>();
                            for (int i = 0; i < ids.length; i++) {
                                ArrayList<Movie> findMovieId = dao.findMovieById(Integer.parseInt(ids[i]));
                                for (Movie m : findMovieId){
                                    movie.add(m);
                                }
                            }
                            log.info("Number of watched movies found, line 329: " + movie.size());
                            Map<String, Integer> countOccurances = new HashMap<>();
                            for (Movie m : movie) {
                                ArrayList<String> genres = m.getGenre();
                                for (String g : genres) {
                                    if (countOccurances.containsKey(g)) {
                                        int count = countOccurances.get(g);
                                        count++;
                                        countOccurances.put(g, count);
                                    } else {
                                        countOccurances.put(g, 1);
                                    }
                                }
                            }
                            Set<String> keyset = countOccurances.keySet();
                            String genre = null;
                            int highestCount = 0;
                            for (String st : keyset) {
                                int currentCount = countOccurances.get(st);
                                if (currentCount > highestCount) {
                                    highestCount = currentCount;
                                    genre = st;
                                }
                            }
                            System.out.println("Receive recommendations from database");
                            log.info("dao.findMovieByGenre(), line 354: " + genre);
                            ArrayList<Movie> findGenre = dao.findMovieByGenre(genre);
                            findGenre.removeAll(movie);
                            while (findGenre.size() > 10) {
                                Random rng = new Random();
                                int removeRec = rng.nextInt(findGenre.size());
                                findGenre.remove(removeRec);
                            }
                            String jsonResponse = getJsonString(findGenre);
                            socketWriter.println(jsonResponse);
                    }
                    /**
                     * message from client is split and some string arguments are parsed and used in the DAO query
                     * returns either success or failure to the client
                     * Local date and time of when the movie was watched is stored
                     */
                    else if (message.startsWith("watchMovieById")){
                        String[] commands = message.split("~");
                        int id = Integer.parseInt(commands[1]);
                        String username = (commands[2]);
                        Timestamp ts = Timestamp.valueOf(LocalDateTime.now(ZoneId.of("UTC")));
                        String string = wdao.watchMovieById(id, username, ts);
                        socketWriter.println(string);
                    }else{
                        socketWriter.println("I'm sorry I don't understand :(");
                        log.info("Unhandled message request.");
                    }
                }
                
                socket.close();
                
            }catch(IOException ex){
                ex.printStackTrace();
            }catch (DaoException ex){
                log.warning(ex.toString());
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
            log.info("Handler for Client " + clientNumber + " is terminating .....");
        }
        /**
         * Converts an ArrayList of Movies into jsonString format
         * @param movie is an arrayList of movies
         * @return is the jsonString
         */
           public String getJsonString(ArrayList<Movie> movie){
           String jsonString = "";
                if (movie.size() == 0){
                        jsonString += "{\"message\": \"failure\",";
                }else{
                    jsonString += "{\"message\": \"success\",";
                }
                jsonString += "\"length\": \"" + movie.size() +"\",";
                jsonString += "\"movies\": [";
                for (Movie m : movie){
                jsonString += "{\"movie_id\": " + m.getMovie_id() + "," + 
                              "\"title\": \"" + m.getTitle() + "\"," +
                              "\"genre\": ["; 
                              ArrayList<String> genres = m.getGenre();
                                  for (String g : genres){
                                      jsonString += "\"" + g + "\",";
                                  }
                jsonString = jsonString.substring(0, jsonString.length() - 1);
                jsonString += "],";
                jsonString += "\"director\": \"" + m.getDirector() + "\"," +
                              "\"runtime\": \"" + m.getRuntime() + "\"," +
                              "\"location\": \"" + m.getLocation() + "\"," +
                              "\"poster\": " + m.hasPoster() + "," +
                              "\"rating\": \"" + m.getRating() + "\"," +
                              "\"format\": \"" + m.getFormat() + "\"," +
                              "\"plot\": \"" + m.getPlot() + "\"," +
                              "\"year\": " + m.getYear() + "," +
                              "\"starring\": \"" + m.getStarring() + "\"," +
                              "\"copies\": " + m.getCopies() + "," +
                              "\"barcode\": \"" + m.getBarcode() + "\"," +
                              "\"user_rating\": " + m.getUser_rating() + "},";    
                }
                if (movie.size() > 0){
                        jsonString = jsonString.substring(0, jsonString.length() - 1);
                }
               jsonString += "]}";
        return jsonString;
        }
           
        /**
         * Simple Json message to represent the success or failure of a command/query
         * @param b boolean representing success or failure
         * @return jsonString
         */
        public String messageSuccessFailure(boolean b){
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
}
