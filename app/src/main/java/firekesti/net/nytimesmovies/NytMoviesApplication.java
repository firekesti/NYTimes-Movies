package firekesti.net.nytimesmovies;

import android.app.Application;

import firekesti.net.nytimesmovies.mylist.MyListStore;

/**
 * Allow instantiation of singletons in the Application onCreate
 */
public class NytMoviesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MyListStore.init(this);
    }
}
