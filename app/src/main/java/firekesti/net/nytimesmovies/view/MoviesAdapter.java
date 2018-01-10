package firekesti.net.nytimesmovies.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import firekesti.net.nytimesmovies.R;
import firekesti.net.nytimesmovies.StringUtils;
import firekesti.net.nytimesmovies.network.models.Result;

/**
 * Created by kkelly on 1/9/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Result> results;

    public MoviesAdapter(List<Result> results) {
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Context context = holder.title.getContext();
        String divider = context.getString(R.string.text_divider);
        Result result = results.get(position);

        // Show or hide the Critic's Pick badge
        holder.criticPick.setVisibility(result.getCriticsPick() == 1 ? View.VISIBLE : View.GONE);

        // Set the title
        holder.title.setText(result.getTitle());

        // Set the year, rating, and runtime, like "2017  |  R  |  1h 23m"
        // Likewise, handle gracefully if one or more values are missing
        StringBuilder sb = new StringBuilder();
        if (result.getYear() != null) {
            sb.append(String.valueOf(result.getYear())).append(divider);
        }
        if (!TextUtils.isEmpty(result.getRating())) {
            sb.append(result.getRating()).append(divider);
        }
        if (result.getRuntimeUs() != null) {
            sb.append(StringUtils.getHourMinuteFromMinutes(result.getRuntimeUs()));
        }
        holder.yearRatingRuntime.setVisibility(sb.length() > 0 ? View.VISIBLE : View.GONE);
        holder.yearRatingRuntime.setText(sb.toString());

        // Set the genres list, adding commas to each except the last
        sb = new StringBuilder();
        List<String> genres = result.getGenres();
        for (int i = 0; i < genres.size(); i++) {
            String genre = genres.get(i);
            sb.append(genre);
            if (i < genres.size() - 1) {
                sb.append(", ");
            }
        }
        holder.genres.setVisibility(sb.length() > 0 ? View.VISIBLE : View.GONE);
        holder.genres.setText(sb.toString());

        // Set the director via a string resource for "Directed by"
        if (result.getDirectors().size() > 0) {
            holder.director.setVisibility(View.VISIBLE);
            holder.director.setText(context.getString(R.string.directed_by, result.getDirectors().get(0)));
        } else {
            holder.director.setVisibility(View.GONE);
        }

        // Set the summary
        if (result.getReviews().size() > 0) {
            holder.summary.setText(result.getReviews().get(0).getSummary());
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private View criticPick;
        private TextView title;
        private TextView yearRatingRuntime;
        private TextView genres;
        private TextView director;
        private TextView summary;

        ViewHolder(View view) {
            super(view);
            criticPick = view.findViewById(R.id.critic_pick);
            title = view.findViewById(R.id.title);
            yearRatingRuntime = view.findViewById(R.id.year_rating_runtime);
            genres = view.findViewById(R.id.genres);
            director = view.findViewById(R.id.director);
            summary = view.findViewById(R.id.summary);
        }
    }
}
