package firekesti.net.nytimesmovies.movies;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import java.util.List;

import firekesti.net.nytimesmovies.R;
import firekesti.net.nytimesmovies.StringUtils;
import firekesti.net.nytimesmovies.mylist.MyListStore;
import firekesti.net.nytimesmovies.network.models.Result;

/**
 * A ViewModel for the Movie adapter
 */
class MovieViewModel {
    private Result result;

    MovieViewModel(Result result) {
        this.result = result;
    }

    int getCriticPickVisibility() {
        return result.getCriticsPick() == 1 ? View.VISIBLE : View.GONE;
    }

    public String getTitle() {
        return result.getTitle();
    }

    String getYearRatingRuntimeText(Context context) {
        return StringUtils.getYearRatingRuntime(result, context);
    }

    String getGenres() {
        // Set the genres list, adding commas to each except the last
        StringBuilder sb = new StringBuilder();
        List<String> genreList = result.getGenres();
        for (int i = 0; i < genreList.size(); i++) {
            String genre = genreList.get(i);
            sb.append(genre);
            if (i < genreList.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    int getDirectorVisibility() {
        return result.getDirectors().size() > 0 ? View.VISIBLE : View.GONE;
    }

    String getDirector(Context context) {
        // Set the director via a string resource for "Directed by"
        return context.getString(R.string.directed_by, result.getDirectors().get(0));
    }

    String getSummary() {
        return result.getReviews().size() > 0 ? result.getReviews().get(0).getSummary() : null;
    }

    String getImageUrl() {
        return result.getReviews().size() > 0
                ? "http://www.nytimes.com/" + result.getReviews().get(0).getImageMediumThreeByTwo() : null;
    }

    String getHeadline() {
        return result.getReviews().size() > 0 ? result.getReviews().get(0).getHeadline() : null;
    }

    String getByline() {
        return result.getReviews().size() > 0 ? result.getReviews().get(0).getByline() : null;
    }

    View.OnClickListener getThumbnailClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (result.getReviews().isEmpty()) {
                    return;
                }
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.nytimes.com/" + result.getReviews().get(0).getReviewUrl()));
                    v.getContext().startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Log.e("MytMovies", "Couldn't open review URL", e);
                }
            }
        };
    }

    private boolean isInMyList() {
        return MyListStore.getInstance().isItemInMyList(result.getImdb());
    }

    String getMyListContentDescription(Context context) {
        return context.getString(isInMyList() ? R.string.desc_remove_from_list : R.string.desc_add_to_list);
    }

    int getMyListDrawable() {
        return isInMyList() ? R.drawable.remove_icon : R.drawable.add_icon;
    }

    View.OnClickListener getMyListClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: UI isn't updated, no LiveData hookup
                if (isInMyList()) {
                    MyListStore.getInstance().removeFromMyList(result.getImdb());
                } else {
                    MyListStore.getInstance().addToMyList(result, v.getContext());
                }
            }
        };
    }
}
