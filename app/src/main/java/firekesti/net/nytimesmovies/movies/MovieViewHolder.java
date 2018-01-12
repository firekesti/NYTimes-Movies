package firekesti.net.nytimesmovies.movies;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import firekesti.net.nytimesmovies.mylist.MyListStore;
import firekesti.net.nytimesmovies.R;
import firekesti.net.nytimesmovies.StringUtils;
import firekesti.net.nytimesmovies.network.models.Result;
import firekesti.net.nytimesmovies.network.models.Review;

/**
 * A ViewHolder for the Movie item that holds the binding logic
 */
class MovieViewHolder extends RecyclerView.ViewHolder {
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

    MovieViewHolder(View view) {
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

    void bind(final Result result) {
        Context context = title.getContext();
        String divider = context.getString(R.string.text_divider);

        // Show or hide the Critic's Pick badge
        criticPick.setVisibility(result.getCriticsPick() == 1 ? View.VISIBLE : View.GONE);

        // Set the title
        title.setText(result.getTitle());

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
        yearRatingRuntime.setVisibility(sb.length() > 0 ? View.VISIBLE : View.GONE);
        yearRatingRuntime.setText(sb.toString());

        // Set the genres list, adding commas to each except the last
        sb = new StringBuilder();
        List<String> genreList = result.getGenres();
        for (int i = 0; i < genreList.size(); i++) {
            String genre = genreList.get(i);
            sb.append(genre);
            if (i < genreList.size() - 1) {
                sb.append(", ");
            }
        }
        genres.setVisibility(sb.length() > 0 ? View.VISIBLE : View.GONE);
        genres.setText(sb.toString());

        // Set the director via a string resource for "Directed by"
        if (result.getDirectors().size() > 0) {
            director.setVisibility(View.VISIBLE);
            director.setText(context.getString(R.string.directed_by, result.getDirectors().get(0)));
        } else {
            director.setVisibility(View.GONE);
        }

        if (result.getReviews().size() > 0) {
            final Review review = result.getReviews().get(0);
            // Set the summary
            summary.setText(review.getSummary());

            // Set the thumbnail
            if (!TextUtils.isEmpty(review.getImageMediumThreeByTwo())) {
                Picasso.with(context)
                        .load("http://www.nytimes.com/" + review.getImageMediumThreeByTwo())
                        .into(thumbnail);
            } else {
                // If empty, we want a placeholder image
                thumbnail.setImageResource(R.drawable.movie_placeholder);
            }

            // Set the headline
            headline.setText(review.getHeadline());

            // Set the byline
            byline.setText(review.getByline());

            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.nytimes.com/" + review.getReviewUrl()));
                        thumbnail.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Log.e("MytMovies", "Couldn't open review URL", e);
                    }
                }
            });
        }

        boolean isInMyList = MyListStore.getInstance().isItemInMyList(result.getImdb());
        myListToggle.setText(isInMyList ? R.string.remove_from_list : R.string.add_to_list);
        myListToggle.setCompoundDrawablesWithIntrinsicBounds(0,
                isInMyList ? R.drawable.remove_icon : R.drawable.add_icon, 0, 0);
        myListToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInMyList = MyListStore.getInstance().isItemInMyList(result.getImdb());
                if (isInMyList) {
                    MyListStore.getInstance().removeFromMyList(result.getImdb());
                } else {
                    MyListStore.getInstance().addToMyList(result.getImdb(), result.getTitle());
                }
                isInMyList = !isInMyList;
                myListToggle.setText(isInMyList ? R.string.remove_from_list : R.string.add_to_list);
                myListToggle.setCompoundDrawablesWithIntrinsicBounds(0,
                        isInMyList ? R.drawable.remove_icon : R.drawable.add_icon, 0, 0);
            }
        });
    }
}
