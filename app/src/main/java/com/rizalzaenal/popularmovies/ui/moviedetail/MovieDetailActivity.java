package com.rizalzaenal.popularmovies.ui.moviedetail;

import android.annotation.SuppressLint;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.R;
import com.rizalzaenal.popularmovies.base.BaseActivity;
import com.rizalzaenal.popularmovies.data.model.Movie;
import com.rizalzaenal.popularmovies.di.component.ActivityComponent;
import java.util.Objects;

public class MovieDetailActivity extends BaseActivity<MovieDetailViewModel> {
  public static final String EXTRA_MOVIE = "MovieDetailActivity.EXTRA_MOVIE";

  Toolbar toolbar;
  ImageView poster;
  TextView title;
  TextView voteAverage;
  TextView releaseDate;
  TextView synopsis;
  ImageView favorite;

  @Override protected int activityLayout() {
    return R.layout.activity_movie_detail;
  }

  @Override protected void injectDependencies(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Override protected void setupViews(@Nullable Bundle savedInstanceState) {
    toolbar = findViewById(R.id.toolbar_movie_detail);
    poster = findViewById(R.id.iv_image);
    title = findViewById(R.id.tv_movie_title);
    voteAverage = findViewById(R.id.tv_vote_average);
    releaseDate = findViewById(R.id.tv_release_date);
    synopsis = findViewById(R.id.tv_plot_synopsis);
    favorite = findViewById(R.id.iv_favorite);
    setSupportActionBar(toolbar);
    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    if (getIntent().hasExtra(EXTRA_MOVIE)) {
      Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
      if (movie != null) {
        setMovieData(movie);
        viewModel.setMovie(movie);
      }
    }

    favorite.setOnClickListener(v -> {
      showSnackBar("Favorited!");
      viewModel.setMovieAsFavorite();
    });
  }

  @SuppressLint("SetTextI18n") private void setMovieData(Movie movie) {
    Glide.with(this)
      .load(BuildConfig.IMAGE_BASE_URL + "w500" + movie.getBackdropPath())
      .into(poster);

    title.setText(movie.getTitle());
    voteAverage.setText(movie.getVoteAverage().toString());
    releaseDate.setText(movie.getReleaseDate());
    synopsis.setText(movie.getOverview());
  }

  @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
