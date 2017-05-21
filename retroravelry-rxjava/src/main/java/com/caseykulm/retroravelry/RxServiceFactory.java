package com.caseykulm.retroravelry;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RxServiceFactory {
  public static RxRetroRavelryService newRxService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RetroRavelryConstants.API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(RxRetroRavelryService.class);
  }

  public static RxRetroRavelryAuthService newRxAuthService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RetroRavelryConstants.API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    return retrofit.create(RxRetroRavelryAuthService.class);
  }
}
