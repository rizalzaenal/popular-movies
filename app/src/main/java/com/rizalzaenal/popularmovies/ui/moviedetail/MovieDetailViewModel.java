package com.rizalzaenal.popularmovies.ui.moviedetail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.rizalzaenal.popularmovies.base.BaseViewModel;
import com.rizalzaenal.popularmovies.data.model.Movie;
import com.rizalzaenal.popularmovies.data.model.MovieTrailers;
import com.rizalzaenal.popularmovies.data.repository.MovieDetailRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailViewModel extends BaseViewModel {
    private static final String TAG = "MovieDetailViewModel";
    static final String FAVORITED = "FAVORITED";
    static final String NOT_FAVORITED = "NOT_FAVORITED";

    private Movie movie;
    private MovieDetailRepository repository;
    private MutableLiveData<String> _favoriteState = new MutableLiveData<String>();
    public LiveData<String> favoriteState = _favoriteState;
    private MutableLiveData<MovieTrailers> _trailers = new MutableLiveData<MovieTrailers>();
    public LiveData<MovieTrailers> trailers = _trailers;

    public MovieDetailViewModel(CompositeDisposable compositeDisposable, MovieDetailRepository repository) {
        super(compositeDisposable);
        this.repository = repository;
    }

    @Override
    protected void onCreate() {
        if (movie != null) {
            Disposable disposable = repository.getMovie(movie.getId())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Movie>() {
                    @Override
                    public void onSuccess(Movie movie) {
                        _favoriteState.postValue(FAVORITED);
                    }

                    @Override
                    public void onError(Throwable e) {
                        _favoriteState.postValue(NOT_FAVORITED);
                    }
                });

            Disposable disposable1 = repository.getMovieTrailers(movie.getId())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<MovieTrailers>() {
                    @Override
                    public void onSuccess(MovieTrailers movieTrailers) {
                        _trailers.postValue(movieTrailers);
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageString.postValue(e.getMessage());
                    }
                });

            compositeDisposable.add(disposable);
            compositeDisposable.add(disposable1);
        }

    }

    public void setOrRemoveMovieAsFavorite() {
        if (movie != null && _favoriteState.getValue().equals(NOT_FAVORITED)) {
            Disposable disposable = repository.insert(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        _favoriteState.postValue(FAVORITED);
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageString.postValue(e.getMessage());
                    }
                });
            compositeDisposable.add(disposable);

        } else {
            Disposable disposable = repository.delete(movie)
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        _favoriteState.postValue(NOT_FAVORITED);
                    }

                    @Override
                    public void onError(Throwable e) {
                        messageString.postValue(e.getMessage());
                        _favoriteState.postValue(FAVORITED);
                    }
                });

            compositeDisposable.add(disposable);
        }
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
