package com.rizalzaenal.popularmovies.ui.mainscreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.rizalzaenal.popularmovies.base.BaseViewModel;
import com.rizalzaenal.popularmovies.data.model.Movie;
import com.rizalzaenal.popularmovies.data.model.ResultInfo;
import com.rizalzaenal.popularmovies.data.repository.MainRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MainViewModel extends BaseViewModel {
  private MainRepository mainRepository;

  private MutableLiveData<List<Movie>> _movieLiveData = new MutableLiveData<>();
  public LiveData<List<Movie>> movieLiveData = _movieLiveData;

  public MainViewModel(CompositeDisposable compositeDisposable, MainRepository mainRepository) {
    super(compositeDisposable);
    this.mainRepository = mainRepository;
  }

  @Override protected void onCreate() {
    fetchPopularMovies();

  }

  public void fetchPopularMovies(){
    Disposable disposable = mainRepository
      .fetchPopularMovies()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeWith(new DisposableSingleObserver<ResultInfo>() {
        @Override public void onSuccess(ResultInfo resultInfo) {
          if (resultInfo != null){
            _movieLiveData.postValue(resultInfo.getResults());
          }
        }

        @Override public void onError(Throwable e) {
          messageString.postValue(e.getMessage());
        }
      });
    compositeDisposable.add(disposable);
  }
}
