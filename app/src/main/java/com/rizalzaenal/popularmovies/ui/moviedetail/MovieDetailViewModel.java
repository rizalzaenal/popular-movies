package com.rizalzaenal.popularmovies.ui.moviedetail;

import com.rizalzaenal.popularmovies.base.BaseViewModel;
import com.rizalzaenal.popularmovies.data.model.Movie;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDetailViewModel extends BaseViewModel {
  private Movie movie;

  public MovieDetailViewModel(CompositeDisposable compositeDisposable) {
    super(compositeDisposable);
  }

  @Override protected void onCreate() {

  }

  public void setMovieAsFavorite(){
    if (movie != null){

    }
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }
}
