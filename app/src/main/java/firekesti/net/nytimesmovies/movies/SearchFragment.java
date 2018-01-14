package firekesti.net.nytimesmovies.movies;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import firekesti.net.nytimesmovies.R;
import firekesti.net.nytimesmovies.network.NytMovieStore;
import firekesti.net.nytimesmovies.network.models.Result;
import retrofit2.Call;
import timber.log.Timber;

/**
 * A Fragment for the "Latest Picks" screen
 */
public class SearchFragment extends Fragment implements NytMovieStore.ResultsListener {

    private ProgressBar loadingSpinner;
    private RecyclerView moviesList;
    private Call call;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.movies_list, container, false);
        moviesList = view.findViewById(R.id.movies_list);
        moviesList.setLayoutManager(new LinearLayoutManager(container.getContext()));
        loadingSpinner = view.findViewById(R.id.loading_spinner);

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        Timber.d("search prepare options menu");
        final MenuItem searchMenuItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_movie_reviews));
        searchMenuItem.expandActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cancelCall();
                loadingSpinner.setVisibility(View.VISIBLE);
                call = NytMovieStore.getMovies(query, SearchFragment.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cancelCall();
    }

    private void cancelCall() {
        // Make sure not to leak the Activity over a long network call
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
        // Make sure to unbind all views to remove LiveData observers in the ViewHolders
        moviesList.setAdapter(null);
    }

    @Override
    public void onResults(List<Result> results) {
        loadingSpinner.setVisibility(View.GONE);
        moviesList.setAdapter(new MoviesAdapter(results));
    }

    @Override
    public void onFailure() {
        loadingSpinner.setVisibility(View.GONE);
        Snackbar.make(loadingSpinner, "There was an error getting movies", Snackbar.LENGTH_SHORT).show();
    }
}
