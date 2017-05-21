package com.example.retroravelry.library;

import com.caseykulm.retroravelry.RetroRavelryAuthService;
import com.caseykulm.retroravelry.ServiceFactory;
import com.caseykulm.retroravelry.responses.library.LibraryResponse;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

public class SyncSearchLibrary extends BaseSearchLibrary {
  @Override void searchLibrary() {
    RetroRavelryAuthService service = ServiceFactory.newAuthService();
    Call<LibraryResponse> call = service.searchLibrary(
        USERNAME, QUERY, QUERY_TYPE, TYPE, SORT, PAGE, PAGE_SIZE);
    try {
      Response<LibraryResponse> response = call.execute();
      printLibrary(response.body());
    } catch (IOException e) {
      printErrors(e);
    }
  }
}
