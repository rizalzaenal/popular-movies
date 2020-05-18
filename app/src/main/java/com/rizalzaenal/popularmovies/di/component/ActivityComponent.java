package com.rizalzaenal.popularmovies.di.component;

import com.rizalzaenal.popularmovies.di.ActivityScope;
import com.rizalzaenal.popularmovies.di.module.ActivityModule;
import com.rizalzaenal.popularmovies.ui.mainscreen.MainActivity;
import com.rizalzaenal.popularmovies.ui.moviedetail.MovieDetailActivity;
import dagger.Component;
import javax.inject.Singleton;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(MainActivity activity);
  void inject(MovieDetailActivity activity);
}
