package com.rizalzaenal.popularmovies.di.module;

import androidx.lifecycle.ViewModelProvider;
import com.rizalzaenal.popularmovies.base.BaseActivity;
import com.rizalzaenal.popularmovies.data.repository.MainRepository;
import com.rizalzaenal.popularmovies.ui.mainscreen.MainViewModel;
import com.rizalzaenal.popularmovies.ui.moviedetail.MovieDetailViewModel;
import com.rizalzaenal.popularmovies.utils.Creator;
import com.rizalzaenal.popularmovies.utils.ViewModelProviderFactory;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {
  private BaseActivity activity;

  public ActivityModule(BaseActivity activity) {
    this.activity = activity;
  }

  @Provides MainViewModel provideMainViewModel(CompositeDisposable compositeDisposable, MainRepository mainRepository) {
    return new ViewModelProvider(activity, new ViewModelProviderFactory<MainViewModel>(
      MainViewModel.class, new Creator<MainViewModel>() {
      @Override public MainViewModel createViewModel() {
        return new MainViewModel(compositeDisposable, mainRepository);
      }
    })).get(MainViewModel.class);
  }

  @Provides MovieDetailViewModel provideMovieDetailViewModel(CompositeDisposable compositeDisposable, MainRepository mainRepository) {
    return new ViewModelProvider(activity, new ViewModelProviderFactory<MovieDetailViewModel>(
      MovieDetailViewModel.class, new Creator<MovieDetailViewModel>() {
      @Override public MovieDetailViewModel createViewModel() {
        return new MovieDetailViewModel(compositeDisposable);
      }
    })).get(MovieDetailViewModel.class);
  }

}
