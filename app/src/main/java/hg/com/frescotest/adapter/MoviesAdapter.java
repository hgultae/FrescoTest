package hg.com.frescotest.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hg.com.frescotest.R;
import hg.com.frescotest.model.Movie;

/**
 * Created by Hurman on 24/02/2016.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    private List<Movie> movies;
    private int rowLayout;
    public Context context;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, final int position) {

        Uri uri = Uri.parse(movies.get(position).getImage());
        holder.draweeView.setImageURI(uri);
        holder.title.setText(movies.get(position).getTitle());
        holder.rating.setText(movies.get(position).getRating()+"");
        holder.year.setText(movies.get(position).getReleaseYear()+"");
        holder.genre.setText(movies.get(position).getGenre().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked on item:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView draweeView;
        public TextView title, rating, year, genre;
        public ViewHolder(View itemView) {
            super(itemView);

            draweeView = (SimpleDraweeView) itemView.findViewById(R.id.icon_movie);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            rating = (TextView) itemView.findViewById(R.id.tv_rating);
            year = (TextView) itemView.findViewById(R.id.tv_year);
            genre = (TextView) itemView.findViewById(R.id.tv_genre);

        }
    }
}
