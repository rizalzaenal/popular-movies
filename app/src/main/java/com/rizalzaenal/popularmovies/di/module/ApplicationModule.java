package com.rizalzaenal.popularmovies.di.module;

import android.app.Application;
import android.content.Context;
import androidx.room.Room;
import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.data.Networking;
import com.rizalzaenal.popularmovies.data.local.AppDB;
import com.rizalzaenal.popularmovies.data.remote.MovieDBService;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class ApplicationModule {
  public static final String APP_CONTEXT = "APP_CONTEXT";
  private static final String DB_NAME = "movie_db";
  private Application application;

  public ApplicationModule(Application application){
    this.application = application;
  }

  @Provides
  @Named(APP_CONTEXT)
  @Singleton
  Context provideAppContext() {
    return application;
  }

  @Provides
  @Singleton
  Application provideApplication(){
    return application;
  }

  @Provides
  CompositeDisposable compositeDisposable(){
    return new CompositeDisposable();
  }

  @Singleton
  @Provides
  MovieDBService provideMovieDBService(){
    return Networking.create(BuildConfig.BASE_URL,
      application.getCacheDir(), 10 * 1024 * 1024L); // 10MB)
  }

  @Singleton
  @Provides
  AppDB provideAppDB() {
    return Room.databaseBuilder(
        application, AppDB.class, DB_NAME)
        .build();
  }

}
