package com.example.retroravelry;

import com.caseykulm.retroravelry.RetroRavelryService;
import com.caseykulm.retroravelry.models.pattern.ListPattern;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiUsage implements Runnable {

  private static final String RAVELRY_API_URL = "https://api.ravelry.com/";

  public static void main(String[] args) {
    ApiUsage apiUsage = new ApiUsage();
    apiUsage.run();
  }

  private RetroRavelryService retroRavelryService;

  private Callback<SearchPatternsResponse> searchCb = new Callback<SearchPatternsResponse>() {
    public void onResponse(Call<SearchPatternsResponse> call,
        Response<SearchPatternsResponse> response) {
      System.out.println("Found patterns:");
      SearchPatternsResponse searchPatternsResponse = response.body();
      List<ListPattern> patterns = searchPatternsResponse.patterns();
      for (ListPattern pattern : patterns) {
        System.out.println(pattern.name());
      }
    }

    public void onFailure(Call<SearchPatternsResponse> call, Throwable throwable) {
      System.out.println(throwable.getMessage());
    }
  };

  public void run() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(RAVELRY_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build();
    retroRavelryService = retrofit.create(RetroRavelryService.class);
    getPatterns();
  }

  private void getPatterns() {
    Call<SearchPatternsResponse> searchPatternsResponseCall =
        retroRavelryService.searchPatterns("", 0, 10, false);
    searchPatternsResponseCall.enqueue(searchCb);
  }

}
