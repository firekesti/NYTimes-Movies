package firekesti.net.nytimesmovies.mylist;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * A store using SharedPreferences to save a list of movie IDs for the user
 */
public class MyListStore {
    // Name of the file where we store all preferences
    private static final String FILE_NAME = "mylist";
    private static final String FILE_NAME_TITLES = "mylist.titles";

    // Singleton instance of this class
    private static MyListStore instance;

    // Shared preferences instance
    private final SharedPreferences ids;
    private final SharedPreferences titles;

    private MyListStore(Context context) {
        ids = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        titles = context.getSharedPreferences(FILE_NAME_TITLES, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        instance = new MyListStore(context);
    }

    public static MyListStore getInstance() {
        return instance;
    }

    public boolean isItemInMyList(String id) {
        return ids.getBoolean(id, false);
    }

    public void addToMyList(String id, String title) {
        ids.edit().putBoolean(id, true).apply();
        titles.edit().putString(id, title).apply();
    }

    public void removeFromMyList(String id) {
        ids.edit().remove(id).apply();
        titles.edit().remove(id).apply();
    }

    Set<String> getAllIds() {
        return ids.getAll().keySet();
    }

    String getTitleForId(String id) {
        return titles.getString(id, null);
    }
}
