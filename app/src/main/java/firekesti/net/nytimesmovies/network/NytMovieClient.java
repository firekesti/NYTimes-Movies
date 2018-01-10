package firekesti.net.nytimesmovies.network;

import android.support.annotation.NonNull;

import java.util.List;

import firekesti.net.nytimesmovies.network.models.ApiResponse;
import firekesti.net.nytimesmovies.network.models.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 *
 */
public final class NytMovieClient {
    private static final String API_BASE_URL = "http://content.api.nytimes.com";
    private static final String API_KEY = "9a8930af832f4fe281998d2181613d5c";

    private NytMovieClient() {
    }

    public static Call<ApiResponse> getMovies(String query, final ResultsListener listener) {
        Call<ApiResponse> call = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(NytMovieApi.class)
                .getMovies(query, API_KEY);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    listener.onResults(response.body().getResults());
                } else {
                    listener.onFailure();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                listener.onFailure();
            }
        });
        return call;
    }

    public interface ResultsListener {
        void onResults(List<Result> results);
        void onFailure();
    }
}
