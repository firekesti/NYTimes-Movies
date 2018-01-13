package firekesti.net.nytimesmovies;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import firekesti.net.nytimesmovies.mylist.MyListStore;
import timber.log.Timber;

/**
 * Allow instantiation of singletons in the Application onCreate
 */
public class NytMoviesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        MyListStore.init(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
