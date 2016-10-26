package com.example.retroravelry;

import com.caseykulm.retroravelry.RetroRavelryAuthService;
import com.caseykulm.retroravelry.RetroRavelryConstants;
import com.caseykulm.retroravelry.RetroRavelryService;
import com.caseykulm.retroravelry.RxRetroRavelryAuthService;
import com.caseykulm.retroravelry.RxRetroRavelryService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ServiceFactory {

  public static RetroRavelryService newService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RetroRavelryConstants.API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    return retrofit.create(RetroRavelryService.class);
  }

  public static RetroRavelryAuthService newAuthService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RetroRavelryConstants.API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    return retrofit.create(RetroRavelryAuthService.class);
  }

  public static RxRetroRavelryService newRxService() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RetroRavelryConstants.API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
