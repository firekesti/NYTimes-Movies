package firekesti.net.nytimesmovies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import firekesti.net.nytimesmovies.network.NytMovieClient;
import firekesti.net.nytimesmovies.network.models.Result;
import firekesti.net.nytimesmovies.view.MoviesAdapter;

public class MainActivity extends AppCompatActivity implements NytMovieClient.ResultsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO user input for query
        NytMovieClient.getMovies("big", MainActivity.this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // TODO user input for query
                NytMovieClient.getMovies("big", MainActivity.this);
            }
        });
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
            // TODO launch a dialog with licenses!
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
