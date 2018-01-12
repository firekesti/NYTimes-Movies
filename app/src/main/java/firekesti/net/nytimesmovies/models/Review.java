package firekesti.net.nytimesmovies.models;

import com.squareup.moshi.Json;

public class Review {

    @Json(name = "review_url")
    private String reviewUrl;
    @Json(name = "headline")
    private String headline;
    @Json(name = "byline")
    private String byline;
    @Json(name = "summary")
    private String summary;
    @Json(name = "scoop_id")
    private String scoopId;
    @Json(name = "publish_date")
    private String publishDate;
    @Json(name = "movie_title")
    private String movieTitle;
    @Json(name = "image_medium_three_by_two")
    private String imageMediumThreeByTwo;
    @Json(name = "video_url")
    private String videoUrl;

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getScoopId() {
        return scoopId;
    }

    public void setScoopId(String scoopId) {
        this.scoopId = scoopId;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getImageMediumThreeByTwo() {
        return imageMediumThreeByTwo;
    }

    public void setImageMediumThreeByTwo(String imageMediumThreeByTwo) {
        this.imageMediumThreeByTwo = imageMediumThreeByTwo;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}
