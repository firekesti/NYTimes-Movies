package firekesti.net.nytimesmovies;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import de.psdev.licensesdialog.LicensesDialog;
import firekesti.net.nytimesmovies.network.NytMovieClient;
import firekesti.net.nytimesmovies.network.models.Result;
import firekesti.net.nytimesmovies.view.MoviesAdapter;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements NytMovieClient.ResultsListener {
    private Call call;
    private RecyclerView moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        moviesList = findViewById(R.id.movies_list);
        moviesList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelCall();
    }

    @Override
    public void onResults(List<Result> results) {
        moviesList.setAdapter(new MoviesAdapter(results));
    }

    @Override
    public void onFailure() {
        Snackbar.make(findViewById(android.R.id.content), "There was an error getting movies", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchMenuItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(getString(R.string.search_movie_reviews));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                cancelCall();
                call = NytMovieClient.getMovies(query, MainActivity.this);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            new LicensesDialog.Builder(this)
                    .setNotices(R.raw.licenses)
                    .build()
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void cancelCall() {
        // Make sure not to leak the Activity over a long network call
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
        moviesList.setAdapter(null);
    }
}
