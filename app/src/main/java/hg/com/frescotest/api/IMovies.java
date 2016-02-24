package hg.com.frescotest.api;

import java.util.List;

import hg.com.frescotest.model.Movie;
import hg.com.frescotest.util.Constants;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Hurman on 24/02/2016.
 */
public interface IMovies {

    @GET(Constants.MOVIES)
    public void getMovies(Callback<List<Movie>> response);

}
