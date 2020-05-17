package com.rizalzaenal.popularmovies.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

abstract public class BaseViewModel extends ViewModel {
  protected CompositeDisposable compositeDisposable;

  public BaseViewModel(CompositeDisposable compositeDisposable ) {
    this.compositeDisposable = compositeDisposable;
  }

  public MutableLiveData<String> messageString = new MutableLiveData<>();
  public MutableLiveData<Integer> messageStringId = new MutableLiveData<>();

  protected abstract void onCreate();


  @Override protected void onCleared() {
    compositeDisposable.dispose();
    super.onCleared();
  }
}
