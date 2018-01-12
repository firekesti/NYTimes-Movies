package firekesti.net.nytimesmovies.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


/**
 * A Data Access Object for the user's list of movies
 */
@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Insert
    void add(Movie... users);

    @Delete
    void remove(Movie user);

    @Query("SELECT * FROM movie WHERE id LIKE :id LIMIT 1")
    Movie findById(String id);
}
