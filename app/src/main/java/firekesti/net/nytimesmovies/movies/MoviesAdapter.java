package firekesti.net.nytimesmovies.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import firekesti.net.nytimesmovies.R;
import firekesti.net.nytimesmovies.network.models.Result;

/**
 * Adapter for the results of the movies API call
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Result> results;

    MoviesAdapter(List<Result> results) {
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(new MovieViewModel(results.get(position)));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    /**
     * A ViewHolder for the Movie item that holds the binding logic
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private View criticPick;
        private TextView title;
        private TextView yearRatingRuntime;
        private TextView genres;
        private TextView director;
        private TextView summary;
        private ImageView thumbnail;
        private TextView headline;
        private TextView byline;
        private TextView myListToggle;

        ViewHolder(View view) {
            super(view);
            criticPick = view.findViewById(R.id.critic_pick);
            title = view.findViewById(R.id.title);
            yearRatingRuntime = view.findViewById(R.id.year_rating_runtime);
            genres = view.findViewById(R.id.genres);
            director = view.findViewById(R.id.director);
            summary = view.findViewById(R.id.summary);
            thumbnail = view.findViewById(R.id.thumbnail);
            headline = view.findViewById(R.id.headline);
            byline = view.findViewById(R.id.byline);
            myListToggle = view.findViewById(R.id.my_list_toggle);
        }

        void bind(final MovieViewModel viewModel) {
            Context context = title.getContext();
            criticPick.setVisibility(viewModel.getCriticPickVisibility());
            title.setText(viewModel.getTitle());
            yearRatingRuntime.setText(viewModel.getYearRatingRuntimeText(context));
            genres.setText(viewModel.getGenres());
            director.setVisibility(viewModel.getDirectorVisibility());
            director.setText(viewModel.getDirector(context));
            summary.setText(viewModel.getSummary());
            Picasso.with(context)
                    .load(viewModel.getImageUrl())
                    .placeholder(R.drawable.movie_placeholder)
                    .into(thumbnail);
            headline.setText(viewModel.getHeadline());
            byline.setText(viewModel.getByline());
            thumbnail.setOnClickListener(viewModel.getThumbnailClickListener());

            myListToggle.setContentDescription(viewModel.getMyListContentDescription(context));
            myListToggle.setCompoundDrawablesWithIntrinsicBounds(0,
                    viewModel.getMyListDrawable(), 0, 0);
            myListToggle.setOnClickListener(viewModel.getMyListClickListener());
        }
    }
}
