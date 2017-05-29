package com.caseykulm.retroravelry;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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

  public static RxRetroRavelryAuthService newRxAuthService(Interceptor interceptor) {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .build();
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RetroRavelryConstants.API_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(RxRetroRavelryAuthService.class);
  }
}
