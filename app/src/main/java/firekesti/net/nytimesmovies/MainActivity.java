package firekesti.net.nytimesmovies;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO user input for query
        call = NytMovieClient.getMovies("big", MainActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Make sure not to leak the Activity over a long network call
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }

    @Override
    public void onResults(List<Result> results) {
        RecyclerView moviesList = findViewById(R.id.movies_list);
        moviesList.setLayoutManager(new LinearLayoutManager(this));
        moviesList.setAdapter(new MoviesAdapter(results));
    }

    @Override
    public void onFailure() {
        Snackbar.make(findViewById(android.R.id.content), "There was an error getting movies", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
