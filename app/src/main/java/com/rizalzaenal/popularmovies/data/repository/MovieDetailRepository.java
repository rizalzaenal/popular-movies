package com.rizalzaenal.popularmovies.data.repository;


import androidx.lifecycle.LiveData;
import com.rizalzaenal.popularmovies.data.local.AppDB;
import com.rizalzaenal.popularmovies.data.model.Movie;
import com.rizalzaenal.popularmovies.data.remote.MovieDBService;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Single;

public class MovieDetailRepository {
    private MovieDBService service;
    private AppDB appDB;

    @Inject public MovieDetailRepository(MovieDBService service, AppDB appDB) {
        this.service = service;
        this.appDB = appDB;
    }

    public Completable insert(Movie movie){
        return appDB.movieDao().insert(movie);
    }

    public Completable delete(Movie movie){
        return appDB.movieDao().delete(movie);
    }

    public LiveData<List<Movie>> getMovies(){
        return appDB.movieDao().getMovies();
    }

    public Single<Movie> getMovie(Integer movieId){
        return appDB.movieDao().getMovie(movieId);
    }
}
