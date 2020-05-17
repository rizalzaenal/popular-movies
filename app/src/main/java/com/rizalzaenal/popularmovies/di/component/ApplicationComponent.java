package com.rizalzaenal.popularmovies.di.component;

import android.app.Application;
import android.content.Context;
import com.rizalzaenal.popularmovies.PopularMoviesApp;
import com.rizalzaenal.popularmovies.data.remote.MovieDBService;
import com.rizalzaenal.popularmovies.di.module.ApplicationModule;
import dagger.Component;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  void inject(PopularMoviesApp application);

  @Named(ApplicationModule.APP_CONTEXT) Context getAppContext();

  CompositeDisposable getCompositeDisposable();
  Application getApplication();
  MovieDBService getService();

}
