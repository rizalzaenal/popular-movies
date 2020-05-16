package com.rizalzaenal.popularmovies.di.module;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
  private Application application;

  public ApplicationModule(Application application){
    this.application = application;
  }

  @Provides Context provideAppContext() {
    return application;
  }


}
