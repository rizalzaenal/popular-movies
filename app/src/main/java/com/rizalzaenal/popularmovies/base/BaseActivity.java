package com.rizalzaenal.popularmovies.base;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.google.android.material.snackbar.Snackbar;
import com.rizalzaenal.popularmovies.PopularMoviesApp;
import com.rizalzaenal.popularmovies.di.component.ActivityComponent;
import com.rizalzaenal.popularmovies.di.component.DaggerActivityComponent;
import com.rizalzaenal.popularmovies.di.module.ActivityModule;
import javax.inject.Inject;

public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {

  @Inject
  protected VM viewModel;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    injectDependencies(buildActivityComponent());
    super.onCreate(savedInstanceState);
    setContentView(activityLayout());
    setupViews(savedInstanceState);
    setupObservers();
    viewModel.onCreate();
  }

  @LayoutRes abstract protected int activityLayout();

  private ActivityComponent buildActivityComponent(){
    PopularMoviesApp app = (PopularMoviesApp) getApplication();
    return DaggerActivityComponent
      .builder()
      .applicationComponent(app.getApplicationComponent())
      .activityModule(new ActivityModule(this))
      .build();
  }

  protected abstract void setupViews(@Nullable Bundle savedInstanceState);

  protected void setupObservers(){
    viewModel.messageString.observe(this, new Observer<String>() {
      @Override public void onChanged(String s) {
        showSnackBar(s);
      }
    });

    viewModel.messageStringId.observe(this, new Observer<Integer>() {
      @Override public void onChanged(Integer integer) {
        showSnackBar(integer);
      }
    });
  }

  protected abstract void injectDependencies(ActivityComponent activityComponent);

  protected void showSnackBar(String message){
    Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
  }

  protected void showSnackBar(@StringRes int messageId){
    Snackbar.make(findViewById(android.R.id.content), getString(messageId), Snackbar.LENGTH_SHORT).show();
  }
}
