package com.rizalzaenal.popularmovies.data;

import com.rizalzaenal.popularmovies.BuildConfig;
import com.rizalzaenal.popularmovies.data.remote.MovieDBService;
import java.io.File;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Networking {

  static final long NETWORK_CALL_TIMEOUT = 60;

  static public MovieDBService create(String baseUrl, String apiKey, File cacheDir, Long cacheSize){

    return new Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(createClient(cacheDir, cacheSize) )
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()
      .create(MovieDBService.class);
  }

  private static OkHttpClient createClient(File cacheDir, Long cacheSize){
    OkHttpClient.Builder client = new OkHttpClient.Builder()
      .cache(new Cache(cacheDir, cacheSize))
      .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
      .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS);

    if (BuildConfig.DEBUG){
      client.addInterceptor(new HttpLoggingInterceptor());
    }
     return client.build();
  }
}
