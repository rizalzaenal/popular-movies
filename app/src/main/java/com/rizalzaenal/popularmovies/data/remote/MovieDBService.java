package com.rizalzaenal.popularmovies.data.remote;

import com.rizalzaenal.popularmovies.data.model.ResultInfo;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBService {

  @GET("movie/popular")
  Single<ResultInfo> getPopularMovies(@Query("api_key") String apiKey);

  @GET("movie/top_rated")
  Single<ResultInfo> getTopRatedMovies(@Query("api_key") String apiKey);
}
