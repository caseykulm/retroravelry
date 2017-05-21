package com.caseykulm.retroravelry;

import java.util.List;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ServiceFactory {
  public static OkHttpClient newOkHttpClient(@Nullable List<Interceptor> networkInterceptors, @Nullable String basicAuthUsername, @Nullable String basicAuthPassword) {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    if (networkInterceptors != null) {
      for (Interceptor interceptor : networkInterceptors) {
        builder.addNetworkInterceptor(interceptor);
      }
    }

    if (basicAuthUsername != null && basicAuthPassword != null) {
      String authToken =
          Credentials.basic(basicAuthUsername, basicAuthPassword);
      BasicAuthInterceptor basicAuthInterceptor = new BasicAuthInterceptor(authToken);
      builder.addNetworkInterceptor(basicAuthInterceptor);
    }

    return builder.build();
  }

  public static RetroRavelryService newService(@NotNull ServiceOptions serviceOptions) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RetroRavelryConstants.API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(newOkHttpClient(
            serviceOptions.getNetworkInterceptors(),
            serviceOptions.getBasicAuthUsername(),
            serviceOptions.getBasicAuthPassword()))
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
}
