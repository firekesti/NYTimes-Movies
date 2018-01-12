package firekesti.net.nytimesmovies.models;

import com.squareup.moshi.Json;

import java.util.List;

public class ApiResponse {

    @Json(name = "has_more")
    private Integer hasMore;
    @Json(name = "results")
    private List<Result> results = null;

    public Integer getHasMore() {
        return hasMore;
    }

    public void setHasMore(Integer hasMore) {
        this.hasMore = hasMore;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
