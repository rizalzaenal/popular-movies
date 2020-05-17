package com.rizalzaenal.popularmovies.data.repository;

import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.data.model.ResultInfo;
import com.rizalzaenal.popularmovies.data.remote.MovieDBService;
import io.reactivex.Single;
import javax.inject.Inject;

public class MainRepository {
  private MovieDBService service;

  @Inject public MainRepository(MovieDBService service) {
    this.service = service;
  }

  public Single<ResultInfo> fetchPopularMovies(){
    return service.getPopularMovies(BuildConfig.API_KEY);
  }

  public Single<ResultInfo> fetchTopRatedMovies(){
    return service.getTopRatedMovies(BuildConfig.API_KEY);
  }


}
