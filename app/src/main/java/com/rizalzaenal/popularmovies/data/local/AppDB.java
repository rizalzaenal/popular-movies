package com.rizalzaenal.popularmovies.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.rizalzaenal.popularmovies.data.model.Movie;

@Database(entities = Movie.class, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    abstract MovieDao movieDao();
}
