package com.example.retroravelry;

import com.caseykulm.retroravelry.RetroRavelryService;
import retrofit2.Retrofit;

public class ApiUsage {

  public static void main(String[] args) {
    Retrofit retrofit = new Retrofit.Builder()
        .addCallAdapterFactory()
        .addConverterFactory()
        .baseUrl("https://api.ravelry.com/")
        .client()
        .build();
  }
}
