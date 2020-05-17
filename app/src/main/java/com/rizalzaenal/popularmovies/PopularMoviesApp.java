package com.rizalzaenal.popularmovies;

import android.app.Application;
import com.rizalzaenal.popularmovies.di.component.ApplicationComponent;
import com.rizalzaenal.popularmovies.di.component.DaggerApplicationComponent;
import com.rizalzaenal.popularmovies.di.module.ApplicationModule;

public class PopularMoviesApp extends Application {
  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    injectDependencies();
  }

  private void injectDependencies(){
    applicationComponent = DaggerApplicationComponent
      .builder()
      .applicationModule(new ApplicationModule(this))
      .build();
    applicationComponent.inject(this);
  }
  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
