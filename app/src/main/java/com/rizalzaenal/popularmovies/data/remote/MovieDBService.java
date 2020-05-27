package com.rizalzaenal.popularmovies.data.remote;

import com.rizalzaenal.popularmovies.data.model.MovieReviews;
import com.rizalzaenal.popularmovies.data.model.MovieTrailers;
import com.rizalzaenal.popularmovies.data.model.MovieResults;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDBService {

  @GET("movie/popular")
  Single<MovieResults> getPopularMovies(@Query("api_key") String apiKey);

  @GET("movie/top_rated")
  Single<MovieResults> getTopRatedMovies(@Query("api_key") String apiKey);

  @GET("movie/{movie_id}/videos")
  Single<MovieTrailers> getMovieTrailers(@Path("movie_id") String movieId, @Query("api_key") String apiKey);

  @GET("movie/{movie_id}/reviews")
  Single<MovieReviews> getMovieReviews(@Path("movie_id") String movieId, @Query("api_key") String apiKey);
}
