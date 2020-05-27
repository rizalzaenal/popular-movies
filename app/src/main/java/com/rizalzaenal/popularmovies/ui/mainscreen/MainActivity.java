package com.rizalzaenal.popularmovies.ui.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rizalzaenal.popularmovies.R;
import com.rizalzaenal.popularmovies.base.BaseActivity;
import com.rizalzaenal.popularmovies.di.component.ActivityComponent;
import com.rizalzaenal.popularmovies.ui.moviedetail.MovieDetailActivity;

public class MainActivity extends BaseActivity<MainViewModel> {
  Toolbar toolbar;
  RecyclerView recyclerView;
  MovieAdapter adapter;

  @Override protected int activityLayout() {
    return R.layout.activity_main;
  }

  @Override protected void injectDependencies(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Override protected void setupViews(@Nullable Bundle savedInstanceState) {
    toolbar = findViewById(R.id.toolbar);
    recyclerView = findViewById(R.id.rv_main);
    setSupportActionBar(toolbar);

    recyclerView.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
    adapter = new MovieAdapter(movie -> {
      Intent intent = new Intent(this, MovieDetailActivity.class);
      intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
      startActivity(intent);
    });
    recyclerView.setAdapter(adapter);
  }

  @Override protected void setupObservers() {
    viewModel.movieLiveData.observe(this, movies -> {
      adapter.setMovieList(movies);
    });

    viewModel.localMovieLiveData.observe(this, movies -> {
      viewModel.setMovieFromDB(movies);
      if (viewModel.getFromFavoriteMenu()){
        if (movies.size() == 0){
          showSnackBar(R.string.favorite_movie_not_found);
        }
        adapter.setMovieList(movies);
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()){
      case R.id.popular:
        viewModel.fetchPopularMovies();
        setTitle(R.string.app_name);
        viewModel.setFromFavoriteMenu(false);
        break;
      case R.id.top_rated:
        viewModel.fetchTopRatedMovies();
        setTitle(getString(R.string.top_rated_movies));
        viewModel.setFromFavoriteMenu(false);
        break;
      case R.id.favorited:
        adapter.setMovieList(viewModel.getMovieFromDB());
        if (viewModel.getMovieFromDB().size() == 0){
          showSnackBar(R.string.favorite_movie_not_found);
        }
        setTitle(getString(R.string.favorited_movies));
        viewModel.setFromFavoriteMenu(true);
    }
    return super.onOptionsItemSelected(item);
  }
}
