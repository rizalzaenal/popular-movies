package com.rizalzaenal.popularmovies.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelProviderFactory<T extends ViewModel>
  implements ViewModelProvider.Factory {
  private Class<T> tClass;
  private Creator<T> creator;

  public ViewModelProviderFactory(Class<T> tClass,
    Creator<T> creator) {
    this.tClass = tClass;
    this.creator = creator;
  }

  @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(tClass)){
      return (T)creator.createViewModel();
    }else {
      throw new IllegalArgumentException("Unknown class name");
    }
  }
}

