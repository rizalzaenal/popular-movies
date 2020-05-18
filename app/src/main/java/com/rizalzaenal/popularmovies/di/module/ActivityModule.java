package com.rizalzaenal.popularmovies.di.module;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import com.rizalzaenal.popularmovies.base.BaseActivity;
import com.rizalzaenal.popularmovies.data.repository.MainRepository;
import com.rizalzaenal.popularmovies.ui.mainscreen.MainViewModel;
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
    return ViewModelProviders.of(activity, new ViewModelProviderFactory<MainViewModel>(
      MainViewModel.class, new Creator<MainViewModel>() {
      @Override public MainViewModel createViewModel() {
        return new MainViewModel(compositeDisposable, mainRepository);
      }
    })).get(MainViewModel.class);
  }

}
