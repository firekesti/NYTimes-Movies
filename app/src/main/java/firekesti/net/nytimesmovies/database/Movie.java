package firekesti.net.nytimesmovies.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * A model for a movie in the database
 */
@Entity
public class Movie {
    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String yearRatingRuntime;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYearRatingRuntime() {
        return yearRatingRuntime;
    }

    public void setYearRatingRuntime(String yearRatingRuntime) {
        this.yearRatingRuntime = yearRatingRuntime;
    }
}
