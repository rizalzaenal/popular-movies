package com.rizalzaenal.popularmovies.ui.mainscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.R;
import com.rizalzaenal.popularmovies.data.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
  private List<Movie> movieList;
  private OnItemClicked listener;

  MovieAdapter(OnItemClicked listener){
    movieList = new ArrayList<Movie>();
    this.listener = listener;
  }

  @NonNull @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
    return new MovieViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    holder.bind(movieList.get(position));
  }

  @Override public int getItemCount() {
    return movieList.size();
  }

  void setMovieList(List<Movie> movieList) {
    this.movieList = movieList;
    notifyDataSetChanged();
  }

  public List<Movie> getMovieList() {
    return movieList;
  }

  class MovieViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    MovieViewHolder(@NonNull View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.iv_poster);
      imageView.setOnClickListener(v -> {
        listener.doOnClick(movieList.get(getAdapterPosition()));
      });
    }

    void bind(Movie movie){
      Glide.with(itemView.getContext())
        .load(BuildConfig.IMAGE_BASE_URL + BuildConfig.IMAGE_SIZE_W185 + movie.getPosterPath())
        .into(imageView);
    }
  }

  public interface OnItemClicked{
    void doOnClick(Movie movie);
  }
}
