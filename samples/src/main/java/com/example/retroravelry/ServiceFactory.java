package com.example.retroravelry;

import com.caseykulm.retroravelry.RetroRavelryService;
import com.caseykulm.retroravelry.RxRetroRavelryService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ServiceFactory {

  private static final String RAVELRY_API_URL = "https://api.ravelry.com/";

  public static RetroRavelryService newService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RAVELRY_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    return retrofit.create(RetroRavelryService.class);
  }

  public static RxRetroRavelryService newRxService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RAVELRY_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    return retrofit.create(RxRetroRavelryService.class);
  }

}
