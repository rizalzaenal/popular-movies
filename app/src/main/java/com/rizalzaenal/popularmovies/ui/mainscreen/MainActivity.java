package com.rizalzaenal.popularmovies.ui.mainscreen;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.R;
import com.rizalzaenal.popularmovies.base.BaseActivity;

public class MainActivity extends BaseActivity {
  TextView text;

  @Override protected int activityLayout() {
    return R.layout.activity_main;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    text = findViewById(R.id.text);
    text.setText(BuildConfig.API_KEY);
  }
}
