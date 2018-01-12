package firekesti.net.nytimesmovies.network;

import firekesti.net.nytimesmovies.models.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * A Retrofit interface for the NY Times Movie API
 */
public interface NytMovieApi {
    @GET("/svc/movies/v3/movies.json")
    Call<ApiResponse> getMovies(
            @Query("query") String query,
            @Query("api-key") String apiKey);
}
