package com.rizalzaenal.popularmovies.data.repository;

import androidx.lifecycle.LiveData;

import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.data.local.AppDB;
import com.rizalzaenal.popularmovies.data.model.Movie;
import com.rizalzaenal.popularmovies.data.model.ResultInfo;
import com.rizalzaenal.popularmovies.data.remote.MovieDBService;

import java.util.List;

import io.reactivex.Single;
import javax.inject.Inject;
import javax.inject.Singleton;

public class MainRepository {
  private MovieDBService service;
  private AppDB appDB;

  @Inject public MainRepository(MovieDBService service, AppDB appDB) {
    this.service = service;
    this.appDB = appDB;
  }

  public Single<ResultInfo> fetchPopularMovies(){
    return service.getPopularMovies(BuildConfig.API_KEY);
  }

  public Single<ResultInfo> fetchTopRatedMovies(){
    return service.getTopRatedMovies(BuildConfig.API_KEY);
  }

  public LiveData<List<Movie>> getMovies(){
    return appDB.movieDao().getMovies();
  }


}
