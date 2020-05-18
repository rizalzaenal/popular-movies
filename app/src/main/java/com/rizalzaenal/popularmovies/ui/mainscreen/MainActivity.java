package com.rizalzaenal.popularmovies.ui.mainscreen;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.R;
import com.rizalzaenal.popularmovies.base.BaseActivity;
import com.rizalzaenal.popularmovies.di.component.ActivityComponent;

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
    adapter = new MovieAdapter();
    recyclerView.setAdapter(adapter);
  }

  @Override protected void setupObservers() {
    viewModel.movieLiveData.observe(this, movies -> {
      adapter.setMovieList(movies);
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
        break;
      case R.id.top_rated:
        viewModel.fetchTopRatedMovies();
        setTitle(getString(R.string.top_rated_movies));
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
