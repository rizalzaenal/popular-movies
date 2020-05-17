package com.rizalzaenal.popularmovies.ui.mainscreen;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.R;
import com.rizalzaenal.popularmovies.base.BaseActivity;
import com.rizalzaenal.popularmovies.di.component.ActivityComponent;

public class MainActivity extends BaseActivity<MainViewModel> {
  TextView text;

  @Override protected int activityLayout() {
    return R.layout.activity_main;
  }

  @Override protected void injectDependencies(ActivityComponent activityComponent) {
    activityComponent.inject(this);
  }

  @Override protected void setupViews(@Nullable Bundle savedInstanceState) {
    text = findViewById(R.id.text);


  }

  @Override protected void setupObservers() {
    viewModel.messageString.observe(this, new Observer<String>() {
      @Override public void onChanged(String s) {

      }
    });

    viewModel.messageStringId.observe(this, new Observer<Integer>() {
      @Override public void onChanged(Integer integer) {

      }
    });
  }



}
