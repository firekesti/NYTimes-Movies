package firekesti.net.nytimesmovies.mylist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.view.View;

import firekesti.net.nytimesmovies.R;
import firekesti.net.nytimesmovies.database.Movie;
import firekesti.net.nytimesmovies.network.models.Result;

/**
 * A ViewModel specific to the My List information of a movie result
 */
public class MyListViewModel {

    private final LiveData<Movie> myListMovie;
    private Observer<Movie> movieObserver;

    public MyListViewModel(Result result, Observer<Movie> movieObserver) {
        this.myListMovie = MyListStore.getInstance().getMyListItemForId(result.getImdb());
        this.movieObserver = movieObserver;
        myListMovie.observeForever(this.movieObserver);
    }

    private boolean isInMyList() {
        return myListMovie.getValue() != null;
    }

    public String getMyListContentDescription(Context context) {
        return context.getString(isInMyList() ? R.string.desc_remove_from_list : R.string.desc_add_to_list);
    }

    public int getMyListDrawable() {
        return isInMyList() ? R.drawable.remove_icon : R.drawable.add_icon;
    }

    public View.OnClickListener getMyListClickListener(final Result result) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInMyList()) {
                    MyListStore.getInstance().removeFromMyList(result.getImdb());
                } else {
                    MyListStore.getInstance().addToMyList(result, v.getContext());
                }
            }
        };
    }

    public void stopObserving() {
        myListMovie.removeObserver(movieObserver);
    }
}
