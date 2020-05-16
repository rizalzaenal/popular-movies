package com.rizalzaenal.popularmovies.di.component;

import com.rizalzaenal.popularmovies.PopularMoviesApp;
import com.rizalzaenal.popularmovies.di.module.ApplicationModule;
import dagger.Component;

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  void inject(PopularMoviesApp application);

}
