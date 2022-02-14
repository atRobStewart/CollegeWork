package DTOs;

import java.util.ArrayList;

/**
 *
 * @author Rob
 */
public class Movie {
    
    private int movie_id;
    private String title;
    private ArrayList<String> genre;
    private String director;
    private String runtime;
    private String plot;
    private String location;
    private boolean poster;
    private String rating;
    private String format;
    private int year;
    private String starring;
    private int copies;
    private String barcode;
    private double user_rating;

    public Movie() {};

    public Movie(int movie_id, String title, ArrayList<String> genre, String director, String runtime, String plot, String location, boolean poster, String rating, String format, int year, String starring, int copies, String barcode, double user_rating) {
        this.movie_id = movie_id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.runtime = runtime;
        this.plot = plot;
        this.location = location;
        this.poster = poster;
        this.rating = rating;
        this.format = format;
        this.year = year;
        this.starring = starring;
        this.copies = copies;
        this.barcode = barcode;
        this.user_rating = user_rating;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.movie_id != other.movie_id) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "Movie{" + "movie_id=" + movie_id + ", title=" + title + ", genre=" + genre + ", director=" + director + ", runtime=" + runtime + ", plot=" + plot + ", location=" + location + ", poster=" + poster + ", rating=" + rating + ", format=" + format + ", year=" + year + ", starring=" + starring + ", copies=" + copies + ", barcode=" + barcode + ", user_rating=" + user_rating + '}';
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean hasPoster() {
        return poster;
    }

    public void setPoster(boolean poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(double user_rating) {
        this.user_rating = user_rating;
    }
}
