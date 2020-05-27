package com.rizalzaenal.popularmovies.data.repository;

import androidx.lifecycle.LiveData;

import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.data.local.AppDB;
import com.rizalzaenal.popularmovies.data.model.Movie;
import com.rizalzaenal.popularmovies.data.model.MovieResults;
import com.rizalzaenal.popularmovies.data.remote.MovieDBService;

import java.util.List;

import io.reactivex.Single;
import javax.inject.Inject;

public class MainRepository {
  private MovieDBService service;
  private AppDB appDB;

  @Inject public MainRepository(MovieDBService service, AppDB appDB) {
    this.service = service;
    this.appDB = appDB;
  }

  public Single<MovieResults> fetchPopularMovies(){
    return service.getPopularMovies(BuildConfig.API_KEY);
  }

  public Single<MovieResults> fetchTopRatedMovies(){
    return service.getTopRatedMovies(BuildConfig.API_KEY);
  }

  public LiveData<List<Movie>> getMovies(){
    return appDB.movieDao().getMovies();
  }


}
