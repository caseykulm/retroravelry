package com.example.retroravelry.patterns;

import com.caseykulm.retroravelry.RetroRavelryService;
import com.caseykulm.retroravelry.ServiceFactory;
import com.caseykulm.retroravelry.ServiceOptions;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class SyncGetPatterns extends BaseGetPatterns {

  @Override void getPatterns() {
    RetroRavelryService service = ServiceFactory.newService(ServiceOptions.Companion.getNone());
    Call<SearchPatternsResponse> call = service.searchPatterns("", 0, 10, false);
    try {
      Response<SearchPatternsResponse> response = call.execute();
      printPatterns(response.body());
    } catch (IOException e) {
      printErrors(e);
    }
  }

}
