package hg.com.frescotest;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hg.com.frescotest.adapter.MoviesAdapter;
import hg.com.frescotest.api.IMovies;
import hg.com.frescotest.model.Movie;
import hg.com.frescotest.util.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        // log level brings logcat output
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        IMovies iMovies = restAdapter.create(IMovies.class);
        iMovies.getMovies(new Callback<List<Movie>>() {
            @Override
            public void success(List<Movie> movies, Response response) {

                dismissDialog();

                Log.i("FrescoTest: ", "Item: " + movies.get(0).getTitle());

                moviesAdapter = new MoviesAdapter(MainActivity.this, movies);
                recyclerView.setAdapter(moviesAdapter);

            }

            @Override
            public void failure(RetrofitError error) {

                dismissDialog();

            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();

        dismissDialog();
    }

    public void dismissDialog(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
}
