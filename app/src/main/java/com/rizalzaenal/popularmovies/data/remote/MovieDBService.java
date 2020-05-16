package com.rizalzaenal.popularmovies.data.remote;

import com.rizalzaenal.popularmovies.data.model.ResultInfo;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface MovieDBService {

  @GET("movie/popular")
  Single<ResultInfo> getPopularMovies();

  @GET("movie/top_rated")
  Single<ResultInfo> getTopRatedMovies();
}
