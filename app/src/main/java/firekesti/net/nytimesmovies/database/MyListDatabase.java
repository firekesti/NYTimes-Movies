package firekesti.net.nytimesmovies.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * The database for the user's list of movies
 */
@Database(entities = {Movie.class}, version = 1)
public abstract class MyListDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
