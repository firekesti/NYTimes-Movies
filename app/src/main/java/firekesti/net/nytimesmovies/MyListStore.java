package firekesti.net.nytimesmovies;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * A store using SharedPreferences to save a list of movie IDs for the user
 */
public class MyListStore {
    // Name of the file where we store all preferences
    private static final String FILE_NAME = "mylist";

    // Singleton instance of this class
    private static MyListStore instance;

    // Shared preferences instance
    private final SharedPreferences prefs;

    private MyListStore(Context context) {
        prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    static void init(Context context) {
        instance = new MyListStore(context);
    }

    public static MyListStore getInstance() {
        return instance;
    }

    public int getMyListSize() {
        return prefs.getAll().size();
    }

    public boolean isItemInMyList(String contentItemId) {
        return prefs.getBoolean(contentItemId, false);
    }

    public void addToMyList(String contentId) {
        prefs.edit().putBoolean(contentId, true).apply();
    }

    public void removeFromMyList(String contentId) {
        prefs.edit().remove(contentId).apply();
    }
}
