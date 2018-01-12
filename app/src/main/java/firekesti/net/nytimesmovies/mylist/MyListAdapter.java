package firekesti.net.nytimesmovies.mylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import firekesti.net.nytimesmovies.R;
import firekesti.net.nytimesmovies.database.Movie;

/**
 * An adapter for a My List view
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<Movie> movies;

    MyListAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item, parent, false);
        return new MyListAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(final MyListAdapter.ViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView yearRatingRuntime;
        TextView myListToggle;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            yearRatingRuntime = view.findViewById(R.id.year_rating_runtime);
            myListToggle = view.findViewById(R.id.my_list_toggle);
        }


        void bind(final Movie movie) {
            title.setText(movie.getTitle());
            yearRatingRuntime.setText(movie.getYearRatingRuntime());

            myListToggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyListStore.getInstance().removeFromMyList(movie);
                }
            });
        }
    }
}
