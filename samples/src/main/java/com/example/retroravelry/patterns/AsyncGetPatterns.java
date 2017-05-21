package com.example.retroravelry.patterns;

import com.caseykulm.retroravelry.RetroRavelryService;
import com.caseykulm.retroravelry.ServiceFactory;
import com.caseykulm.retroravelry.ServiceOptions;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncGetPatterns extends BaseGetPatterns {

  private Callback<SearchPatternsResponse> searchCb = new Callback<SearchPatternsResponse>() {
    public void onResponse(Call<SearchPatternsResponse> call, Response<SearchPatternsResponse> response) {
      printPatterns(response.body());
    }

    public void onFailure(Call<SearchPatternsResponse> call, Throwable throwable) {
      printErrors(throwable);
    }
  };

  @Override void getPatterns() {
    RetroRavelryService service = ServiceFactory.newService(ServiceOptions.Companion.getNone());
    Call<SearchPatternsResponse> call = service.searchPatterns("", 0, 10, false);
    call.enqueue(searchCb);
  }

}
