package BusinessObjects;

import DTOs.Movie;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
/**
 *
 * @author Rob
 */
public class Client {
    public static void main(String[] args){
        Client client = new Client();
        client.start();
    }
    public void start(){
        Scanner in = new Scanner(System.in);
        try {
            // connect to server socket, and open new socket
            Socket socket = new Socket("localhost", 8080);  

            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );
            
            OutputStream os = socket.getOutputStream();

            PrintWriter socketWriter= new PrintWriter(os, true);// true=> auto flush buffers
          
            Scanner socketReader = new Scanner(socket.getInputStream());
            
            boolean hasFinished = false;
            
            System.out.println("Please enter your username: ");
            String username = getValidString(in);
            
            System.out.println("Client: This Client is running and has connected to the server");
            do{
                System.out.println("");
                System.out.println("To Execute any of the following commands, copy and paste them into the white space below the 'exit' option:\n");
                System.out.println("Type 'recommended' to get a list of recomendations based on what you have watched");
                System.out.println("findAllMovies");
                System.out.println("findMovieById");
                System.out.println("findMovieByTitle");
                System.out.println("findMovieByGenre");
                System.out.println("addMovie");
                System.out.println("deleteMovieById");
                System.out.println("updateMovieTitleById");
                System.out.println("watchMovieById");
                System.out.println("exit");
                System.out.println("Please enter a command: ");
            
            String command = in.nextLine();  // read a command from the user

              
            
            switch (command){
                /**
                 * Command is written to the server socket
                 * The response from the server is stored
                 * The response is then changed from  jsonString to jsonObject
                 * message retrieves the command success/failure
                 * If the message equals failure the command failed
                 * On success all movie data is then referenced in the jsonArray
                 * Loop cycles through each element of the array
                 * Each element is turned back into a Movie Object
                 * Data is then printed to the command line
                 */
                case "findAllMovies":
                    socketWriter.println(command);  
                    String response = socketReader.nextLine();
                    JsonObject movieObject = jsonFromString(response);
                    String message = movieObject.getString("message");
                        if (message.equalsIgnoreCase("failure")){
                            System.out.println("There are no movies");
                        }else{
                            JsonArray requestArray = movieObject.getJsonArray("movies");
                            for (int i = 0; i < requestArray.size(); i++){ 
                                Movie mo = movieFromJson(requestArray.getJsonObject(i));
                                System.out.print("Movie: " + mo.toString() + "\n");
                            }
                        }
                break;
                /**
                 * Similar functionality to findAllMovies
                 * Users are prompted to enter a movie id, which is validated
                 * The Tilde in command is the delimiter that the server uses to separate different inputs
                 * Movie with specific id is printed, so long as it exists
                 * 
                 * See findAllMovies case comments for in detail explanation of the code
                 */
                case "findMovieById":
                    System.out.println("Enter Movie Id: ");
                    int id = getValidInteger(in);
                    in.nextLine();
                    command += "~" + id;
                    socketWriter.println(command);  
                    response = socketReader.nextLine();
                    movieObject = jsonFromString(response);
                    message = movieObject.getString("message");
                            if (message.equalsIgnoreCase("failure")){
                                System.out.println("There are no movies with that Id");
                            }else{
                                JsonArray requestArray = movieObject.getJsonArray("movies");
                                for (int i = 0; i < requestArray.size(); i++){ 
                                    Movie mo = movieFromJson(requestArray.getJsonObject(i));
                                    System.out.print("Movie: " + mo.toString() + "\n");
                                }
                        }    
                break;
                /**
                 * Similar functionality to findMovieById
                 * Users are prompted to enter a movie title, which is validated
                 * 
                 * See findAllMovies case comments for in detail explanation of the code
                 */
                case "findMovieByTitle":
                    System.out.println("Enter Movie Title: ");
                    String title = getValidString(in);
                    command += "~" + title;
                    socketWriter.println(command);  
                    response = socketReader.nextLine();
                    movieObject = jsonFromString(response);
                    message = movieObject.getString("message");
                            if (message.equalsIgnoreCase("failure")){
                                System.out.println("There are no movies with that Title");
                            }else{
                            JsonArray requestArray = movieObject.getJsonArray("movies");
                                for (int i = 0; i < requestArray.size(); i++){ 
                                    Movie mo = movieFromJson(requestArray.getJsonObject(i));
                                    System.out.print("Movie: " + mo.toString() + "\n");
                                }
                        }    
                break;
                /**
                 * Similar functionality to findMovieByTitle
                 * Users are prompted to enter a movie title, which is validated
                 * 
                 * See findAllMovies case comments for in detail explanation of the code
                 */
                case "findMovieByGenre":
                    System.out.println("Enter Movie Genre: ");
                    String genre = getValidString(in);
                    command += "~" + genre;
                    socketWriter.println(command);  // write command to socket
                    response = socketReader.nextLine();
                    movieObject = jsonFromString(response);
                    message = movieObject.getString("message");
                            if (message.equalsIgnoreCase("failure")){
                                System.out.println("There are no movies with that Genre");
                            }else{
                        JsonArray requestArray = movieObject.getJsonArray("movies");
                                for (int i = 0; i < requestArray.size(); i++){ 
                                    Movie mo = movieFromJson(requestArray.getJsonObject(i));
                                    System.out.print("Movie: " + mo.toString() + "\n");
                                }
                        }    
                break;
                /**
                 * User must enter all new movie details in the presented order, id is auto filled
                 * On entering data into the final field and all data is valid, the movie will be added to the database
                 */
                case "addMovie":
                    System.out.println("Enter Movie Title: ");
                            String titles = getValidString(in);
                            command += "~" + titles;
                            System.out.println("Enter Movie Genre, seperated by comma: ");
                            String genres = getValidString(in);
                            command += "~" + genres;
                            System.out.println("Enter Movie Director: ");
                            String director = getValidString(in);
                            command += "~" + director;
                            System.out.println("Enter Movie Runtime: ");
                            String runtime = getValidString(in);
                            command += "~" + runtime;
                            System.out.println("Enter Movie Plot: ");
                            String plot = getValidString(in);
                            command += "~" + plot;
                            System.out.println("Enter Movie Location: ");
                            String location = getValidString(in);
                            command += "~" + location;
                            System.out.println("Enter Movie Poster: ");
                            String posters = getValidString(in);
                            boolean poster;
                                if (posters.equalsIgnoreCase("Yes")){
                                    poster = true;
                                }else{
                                    poster = false;
                                }
                                command += "~" + poster;
                            System.out.println("Enter Movie Rating: ");
                            String rating = getValidString(in);
                            command += "~" + rating;
                            System.out.println("Enter Movie Format: ");
                            String format = getValidString(in);
                            command += "~" + format;
                            System.out.println("Enter Movie Year: ");
                            int year = getValidInteger(in);
                            in.nextLine();
                            command += "~" + year;
                            System.out.println("Enter Movie Starring: ");
                            String starring = getValidString(in);
                            command += "~" + starring;
                            System.out.println("Enter Movie Copies: ");
                            int copies = getValidInteger(in);
                            in.nextLine();
                            command += "~" + copies;
                            System.out.println("Enter Movie Barcode: ");
                            String barcode = getValidString(in);
                            command += "~" + barcode;
                            System.out.println("Enter Movie User Rating: ");
                            double userRating = getValidDouble(in);
                            in.nextLine();
                            command += "~" + userRating;
                            
                            socketWriter.println(command); 
                            response = socketReader.nextLine();
                            movieObject = jsonFromString(response);
                            message = movieObject.getString("message");
                                if (message.equalsIgnoreCase("failure")){
                                    System.out.println("Couldn't add movie to database");
                                }else{
                                    System.out.println("Movie successfully added");
                                }
                break;
                /**
                 * Similar functionality to findMovieById
                 * If the movie exists it will be deleted
                 * 
                 * See findAllMovies case comments for in detail explanation of the code
                 */
                case "deleteMovieById":
                    System.out.println("Enter Movie Id: ");
                    id = getValidInteger(in);
                    in.nextLine();
                    command += "~" + id;
                    socketWriter.println(command); 
                    response = socketReader.nextLine();
                    movieObject = jsonFromString(response);
                    message = movieObject.getString("message");
                            if (message.equalsIgnoreCase("failure")){
                                System.out.println("There was an error when deleting the movie");
                            }else{
                                System.out.println("Movie successfully deleted.");
                            }    
                break;
                /**
                 * User will search a movie by it id
                 * Then prompted to enter a new movie title
                 * On success the newly updated movie will be printed 
                 * 
                 * See findAllMovies case comments for in detail explanation of the code
                 */
                case "updateMovieTitleById":
                    System.out.println("Enter Movie id");
                    id = getValidInteger(in);
                    command += "~" + id;
                    in.nextLine();
                    System.out.println("Enter Updated Movie Title: ");
                    title = getValidString(in);   
                    command += "~" + title;
                    socketWriter.println(command);
                    response = socketReader.nextLine();
                    movieObject = jsonFromString(response);
                    message = movieObject.getString("message");
                            if (message.equalsIgnoreCase("failure")){
                                System.out.println("There was an error when updating the movie");
                            }else{
                                JsonArray requestArray = movieObject.getJsonArray("movies");
                                System.out.println("Movie updated to: ");
                            for (int i = 0; i < requestArray.size(); i++){ 
                                    Movie mo = movieFromJson(requestArray.getJsonObject(i));
                                    System.out.print("Movie: " + mo.toString() + "\n");
                                }
                        }
                break;
                /**
                 * A unique user enters a movie id of a movie that they have watched
                 * This gets stored on a separate watched move database table
                 * 
                 * See findAllMovies case comments for in detail explanation of the code
                 */
                case "watchMovieById":
                    System.out.println("Enter Movie Id: ");
                    id = getValidInteger(in);
                    in.nextLine();
                    command += "~" + id + "~" + username;
                    socketWriter.println(command);
                    response = socketReader.nextLine();
                    movieObject = jsonFromString(response);
                    message = movieObject.getString("message");
                            if (message.equalsIgnoreCase("failure")){
                                System.out.println("There are no movies with that Id");
                            }else{
                                System.out.println("Movie has been watched");
                        }    
                break;
                /**
                 * Client requests the server for recommended movies based on watched movies Genres per user
                 * Ten unwatched movies will be returned randomly 
                 * 
                 * See findAllMovies case comments for in detail explanation of the code
                 */
                case "recommended":
                    command += "~" + username;
                    socketWriter.println(command);
                    response = socketReader.nextLine();
                    movieObject = jsonFromString(response);
                    message = movieObject.getString("message");
                            if (message.equalsIgnoreCase("failure")){
                                System.out.println("There was an error getting recommendations");
                            }else{
                                JsonArray requestArray = movieObject.getJsonArray("movies");
                                System.out.println("Recommended Movies based on Genres are: ");
                            for (int i = 0; i < requestArray.size(); i++){ 
                                    Movie mo = movieFromJson(requestArray.getJsonObject(i));
                                    System.out.print("Movie: " + mo.toString() + "\n");
                                }
                        }
                break;
                case "exit":
                    hasFinished = true;
                break;
                default:
                    System.out.println("Incorrect input, Please try again");
                break;
           } 
           System.out.println("Press Enter to Continue: ");
           in.nextLine();
        }while (!hasFinished);
            socketWriter.close();
            socketReader.close();
            socket.close();

        }catch(IOException e){
            System.out.println("Client message: IOException: " + e);
        }
        
    }
    /**
     * The JsonObject gets converted back into a Movie Object
     * @param rs is a JsonObject representing a Movie Object
     * @return is a movie object
     */
    public static Movie movieFromJson(JsonObject rs){
        
        int movie_id = rs.getInt("movie_id");
        String title = rs.getString("title");
        JsonArray genre = rs.getJsonArray("genre");
        ArrayList<String> genreList = new ArrayList<>();
            for (int j = 0; j < genre.size(); j++){
                genreList.add(genre.getString(j));
            }
        String director = rs.getString("director");
        String runtime = rs.getString("runtime");
        String plot = rs.getString("plot");
        String location = rs.getString("location");
        boolean poster = rs.getBoolean("poster");
        String rating = rs.getString("rating");
        String format = rs.getString("format");
        int year = rs.getInt("year");
        String starring = rs.getString("starring");
        int copies = rs.getInt("copies");
        String barcode = rs.getString("barcode");
        //String user_ratings = rs.getString("user_rating");
        double user_rating = rs.getJsonNumber("user_rating").doubleValue();

        Movie movie = new Movie (movie_id, title, genreList, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
        
        return movie;
    }
    
    /**
     * Converts the jsonString retrieved from the server into JsonObject
     * @param js is a String representing JsonObject
     * @return is a JsonObject
     */
    public static JsonObject jsonFromString (String js){
        JsonReader reader = Json.createReader(new StringReader(js));
        JsonObject object = reader.readObject();
        reader.close();
        
        return object;
    }
    
    /**
     * Ensures the user inputs an integer
     * Additionally this will stop the application from failing
     * @param s is the Scanner for user inputted data
     * @return correctly entered user data
     */
    public static int getValidInteger(Scanner s){
        boolean isValid = false;
        int num = 0;
        while (!isValid){
            if (s.hasNextInt()){
                num = s.nextInt();
                isValid = true;
            }else{
                System.out.println("Invalid input, please enter a number");
                s.nextLine();
            }
        }
        return num;
    }
 
    /**
     * Ensures the user inputs a double
     * Additionally this will stop the application from failing
     * @param s is the Scanner for user inputted data
     * @return correctly entered user data
     */
    public static double getValidDouble(Scanner s){
        boolean isValid = false;
        double num = 0;
        while (!isValid){
            if (s.hasNextDouble()){
                num = s.nextDouble();
                isValid = true;
            }else{
                System.out.println("Invalid input, please enter a number");
                s.nextLine();
            }
        }
        return num;
    }
    
    /**
     * String can take all data
     * double quotes are removed to minimise SQL injection
     * tilde is removed because its a delimiter on the server
     * @param s is the Scanner for user inputted data
     * @return correctly enter user data
     */
    public static String getValidString(Scanner s){
        String str = s.nextLine();
        str = str.replace('"', '/');
        str = str.replace('~', '/');
        return str;
    }
}
