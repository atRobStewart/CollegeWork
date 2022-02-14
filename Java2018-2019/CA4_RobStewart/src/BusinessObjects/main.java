//package BusinessObjects;
///**
// *
// * @author Rob
// */
//import DTOs.Movie;
//import DAOs.MySqlMovieDao;
//import DAOs.MovieDaoInterface;
//import Exceptions.DaoException;
//import java.io.InputStream;
//import java.io.StringReader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//import javax.json.Json;
//import javax.json.JsonArray;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//
//public class main {
//    
//    public static void main(String[] args) {
//        MovieDaoInterface IMovieDao = new MySqlMovieDao();
//        Scanner s = new Scanner(System.in);
//        try{
//            do{
//                System.out.println("To Execute any of the following commands, copy and paste them into the white space below the 'exit' option:\n");
//                System.out.println("findAllMovies");
//                System.out.println("findMovieById");
//                System.out.println("findMovieByTitle");
//                System.out.println("findMovieByTitleGenre");
//                System.out.println("addMovie");
//                System.out.println("deleteMovieById");
//                System.out.println("updateMovieTitleById");
//                System.out.println("exit");
//                
//                String line = s.nextLine();
//                String[] words = line.split(" ");
//                String command = words[0];
//                switch(command){
//                    case "findAllMovies":
//                        String string = IMovieDao.findAllMovies();
//                        JsonObject movies = jsonFromString(string);
//                        //System.out.println(movies);
//                        String message = movies.getString("message");
//                            if (message.equalsIgnoreCase("failure")){
//                                System.out.println("There are no movies");
//                            }else{
//                        JsonArray requestArray = movies.getJsonArray("movies");
//                            for (int i = 0; i < requestArray.size(); i++){
//                                JsonObject rs = requestArray.getJsonObject(i);
//                                System.out.println("Movie: " + movieFromJson(rs).toString() + "\n");
//                            }
//                        }
//                        break;
//                        
//                    case "findMovieById":
//                        int id = Integer.parseInt(words[1]);
//                        string = IMovieDao.findMovieById(id);
//                        movies = jsonFromString(string);
//                        message = movies.getString("message");
//                            if (message.equalsIgnoreCase("failure")){
//                                System.out.println("There are no movies with that Id");
//                            }else{
//                        JsonArray requestArray = movies.getJsonArray("movies");
//                            for (int i = 0; i < requestArray.size(); i++){
//                                JsonObject rs = requestArray.getJsonObject(i);
//                                System.out.println("Movie: " + movieFromJson(rs).toString() + "\n");
//                            }
//                        }    
//                        break;
//                        
//                    case "findMovieByTitle":
//                        String title = "";
//                            for(int i = 1; i < words.length; i++){
//                                title += words[i] + " ";
//                            }
//                        title = title.trim();
//                        string = IMovieDao.findMovieByTitle(title);
//                        movies = jsonFromString(string);
//                        message = movies.getString("message");
//                            if (message.equalsIgnoreCase("failure")){
//                                System.out.println("There are no movies with that Title");
//                            }else{
//                        JsonArray requestArray = movies.getJsonArray("movies");
//                            for (int i = 0; i < requestArray.size(); i++){
//                                JsonObject rs = requestArray.getJsonObject(i);
//                                System.out.println("Movie: " + movieFromJson(rs).toString() + "\n");
//                            }
//                        }    
//                        break;
//                    case "findMovieByTitleGenre":
//                            title = "";
//                                for(int i = 1; i < words.length - 1; i++){
//                                    title += words[i] + " ";
//                                }
//                            title = title.trim();
//                            string = IMovieDao.findMovieByGenre(words[words.length-1]);
//                            movies = jsonFromString(string);
//                            message = movies.getString("message");
//                                if (message.equalsIgnoreCase("failure")){
//                                    System.out.println("There are no movies with that Title and Genre");
//                                }else{
//                            JsonArray requestArray = movies.getJsonArray("movies");
//                                for (int i = 0; i < requestArray.size(); i++){
//                                JsonObject rs = requestArray.getJsonObject(i);
//                                System.out.println("Movie: " + movieFromJson(rs).toString() + "\n");
//                            }
//                        }    
//                        break;
//                    case "addMovie":
//                            System.out.println("Enter Movie Title: ");
//                            String titles = s.nextLine();
//                            System.out.println("Enter Movie Genre, seperated by comma: ");  // use regex here to 
//                            String genres = s.nextLine();
//                            System.out.println("Enter Movie Director: ");
//                            String director = s.nextLine();
//                            System.out.println("Enter Movie Runtime: ");
//                            String runtime = s.nextLine();
//                            System.out.println("Enter Movie Plot: ");
//                            String plot = s.nextLine();
//                            System.out.println("Enter Movie Location: ");
//                            String location = s.nextLine();
//                            System.out.println("Enter Movie Poster: ");
//                            String posters = s.nextLine();
//                            boolean poster;
//                                if (posters.equalsIgnoreCase("Yes")){
//                                    poster = true;
//                                }else{
//                                    poster = false;
//                                }
//                            System.out.println("Enter Movie Rating: ");
//                            String rating = s.nextLine();
//                            System.out.println("Enter Movie Format: ");
//                            String format = s.nextLine();
//                            System.out.println("Enter Movie Year: ");
//                            int year = s.nextInt();
//                            s.nextLine();
//                            System.out.println("Enter Movie Starring: ");
//                            String starring = s.nextLine();
//                            System.out.println("Enter Movie Copies: ");
//                            int copies = s.nextInt();
//                            s.nextLine();
//                            System.out.println("Enter Movie Barcode: ");
//                            String barcode = s.nextLine();
//                            System.out.println("Enter Movie User Rating: ");
//                            double userRating = s.nextDouble();
//                            s.nextLine();
//                            
//                            string = IMovieDao.addMovie(titles, genres, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, userRating);
//                            movies = jsonFromString(string);
//                            message = movies.getString("message");
//                                if (message.equalsIgnoreCase("failure")){
//                                    System.out.println("Couldn't add movie to database");
//                                }else{
//                                    System.out.println("Movie successfully added");
//                                }
//                        break;
//                    case "deleteMovieById":
//                        id = Integer.parseInt(words[1]);
//                        string = IMovieDao.deleteMovieById(id);
//                        movies = jsonFromString(string);
//                        message = movies.getString("message");
//                            if (message.equalsIgnoreCase("failure")){
//                                System.out.println("There was an error when deleting the movie");
//                            }else{
//                                System.out.println("Movie successfully deleted.");
//                            }
//                        break;
//                    case "updateMovieTitleById":
//                        System.out.println("Enter Movie id");
//                        id = s.nextInt();
//                        s.nextLine();
//                        System.out.println("Enter Updated Movie Title: ");
//                        title = s.nextLine();
//                        
//                        string = IMovieDao.updateMovieTitleById(id, title);
//                        movies = jsonFromString(string);
//                        message = movies.getString("message");
//                            if (message.equalsIgnoreCase("failure")){
//                                System.out.println("There was an error when updating the movie");
//                            }else{
//                               JsonArray requestArray = movies.getJsonArray("movies");
//                                System.out.println("Movie updated to: ");
//                            for (int i = 0; i < requestArray.size(); i++){
//                                JsonObject rs = requestArray.getJsonObject(i);
//                                System.out.println("Movie: " + movieFromJson(rs).toString() + "\n");
//                            }
//                        }   
//                        break;
//                        
//                    case "exit":
//                        System.exit(0);
//                        break;
//                }
//            }while(true);
//                
//                
//      
//        }catch (DaoException e){
//            e.printStackTrace();
//        }
//    }
//    
//    public static Movie movieFromJson(JsonObject rs){
//        
//        int movie_id = rs.getInt("movie_id");
//        String title = rs.getString("title");
//        JsonArray genre = rs.getJsonArray("genre");
//        ArrayList<String> genreList = new ArrayList<>();
//            for (int j = 0; j < genre.size(); j++){
//                genreList.add(genre.getString(j));
//            }
//        String director = rs.getString("director");
//        String runtime = rs.getString("runtime");
//        String plot = rs.getString("plot");
//        String location = rs.getString("location");
//        boolean poster = rs.getBoolean("poster");
//        String rating = rs.getString("rating");
//        String format = rs.getString("format");
//        int year = rs.getInt("year");
//        String starring = rs.getString("starring");
//        int copies = rs.getInt("copies");
//        String barcode = rs.getString("barcode");
//        //String user_ratings = rs.getString("user_rating");
//        double user_rating = rs.getJsonNumber("user_rating").doubleValue();
//
////                            if (user_ratings != null && !user_ratings.equalsIgnoreCase("null") && !user_ratings.equalsIgnoreCase("n/a")){
////                                user_rating = Double.parseDouble(user_ratings);
////                            }
//        //ArrayList<String> genreList = new ArrayList<>(Arrays.asList(genre.split(","))); // splits the string of genres into an array of genres and puts them into a new Arraylist of genres
//
//        Movie movie = new Movie (movie_id, title, genreList, director, runtime, plot, location, poster, rating, format, year, starring, copies, barcode, user_rating);
//        
//        
//        return movie;
//    }
//    
//    public static JsonObject jsonFromString (String js){
//        JsonReader reader = Json.createReader(new StringReader(js));
//        JsonObject object = reader.readObject();
//        reader.close();
//        
//        return object;
//    } 
//}
