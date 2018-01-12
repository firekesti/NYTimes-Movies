package firekesti.net.nytimesmovies.network.models;

import com.squareup.moshi.Json;

import java.util.List;

public class Result {

    @Json(name = "imdb")
    private String imdb;
    @Json(name = "critics_pick")
    private Integer criticsPick;
    @Json(name = "title")
    private String title;
    @Json(name = "year")
    private Integer year;
    @Json(name = "directors")
    private List<String> directors = null;
    @Json(name = "writers")
    private List<String> writers = null;
    @Json(name = "actors")
    private List<Actor> actors = null;
    @Json(name = "rating")
    private String rating;
    @Json(name = "runtime_us")
    private Integer runtimeUs;
    @Json(name = "genres")
    private List<String> genres = null;
    @Json(name = "release_date_us")
    private Object releaseDateUs;
    @Json(name = "ticket_url")
    private Object ticketUrl;
    @Json(name = "updated")
    private String updated;
    @Json(name = "reviews")
    private List<Review> reviews = null;

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public Integer getCriticsPick() {
        return criticsPick;
    }

    public void setCriticsPick(Integer criticsPick) {
        this.criticsPick = criticsPick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getRuntimeUs() {
        return runtimeUs;
    }

    public void setRuntimeUs(Integer runtimeUs) {
        this.runtimeUs = runtimeUs;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Object getReleaseDateUs() {
        return releaseDateUs;
    }

    public void setReleaseDateUs(Object releaseDateUs) {
        this.releaseDateUs = releaseDateUs;
    }

    public Object getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(Object ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

}
