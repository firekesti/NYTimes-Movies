package firekesti.net.nytimesmovies.mylist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import firekesti.net.nytimesmovies.StringUtils;
import firekesti.net.nytimesmovies.database.Movie;
import firekesti.net.nytimesmovies.database.MyListDatabase;
import firekesti.net.nytimesmovies.network.models.Result;

/**
 * A store using SharedPreferences to save a list of movie IDs for the user
 */
public class MyListStore {
    // Singleton instance of this class
    private static MyListStore instance;

    // The Database for my list
    private MyListDatabase db;

    private MyListStore(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(), MyListDatabase.class, "my-list")
                // TODO temp
                .allowMainThreadQueries()
                .build();
    }

    public static void init(Context context) {
        instance = new MyListStore(context);
    }

    public static MyListStore getInstance() {
        return instance;
    }

    public void addToMyList(Result result, Context context) {
        Movie movie = new Movie();
        movie.setId(result.getImdb());
        movie.setTitle(result.getTitle());
        movie.setYearRatingRuntime(StringUtils.getYearRatingRuntime(result, context));
        db.movieDao().add(movie);
    }

    public void removeFromMyList(String id) {
        db.movieDao().remove(db.movieDao().findById(id));
    }

    public void removeFromMyList(Movie movie) {
        db.movieDao().remove(movie);
    }

    public LiveData<List<Movie>> getMyList() {
        return db.movieDao().getAll();
    }

    public LiveData<Movie> getMyListItemForId(String id) {
        return db.movieDao().findByIdObservable(id);
    }
}
