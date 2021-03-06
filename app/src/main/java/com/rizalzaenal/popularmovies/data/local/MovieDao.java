package com.rizalzaenal.popularmovies.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.rizalzaenal.popularmovies.data.model.Movie;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Insert
    Completable insert(Movie movie);

    @Query("SELECT * FROM movie_table")
    LiveData<List<Movie>> getMovies();

    @Query("SELECT * FROM movie_table WHERE id = :id")
    Single<Movie> getMovie(Integer id);

    @Delete
    Completable delete(Movie movie);
}
