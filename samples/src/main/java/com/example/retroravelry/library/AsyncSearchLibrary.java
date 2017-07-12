package com.example.retroravelry.library;

import com.caseykulm.retroravelry.RetroRavelryService;
import com.caseykulm.retroravelry.ServiceFactory;
import com.caseykulm.retroravelry.responses.library.LibraryResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AsyncSearchLibrary extends BaseSearchLibrary {

  private Callback<LibraryResponse> callback = new Callback<LibraryResponse>() {
    public void onResponse(Call<LibraryResponse> call, Response<LibraryResponse> response) {
      printLibrary(response.body());
    }

    public void onFailure(Call<LibraryResponse> call, Throwable throwable) {
      printErrors(throwable);
    }
  };

  @Override void searchLibrary() {
    RetroRavelryService service = ServiceFactory.newAuthService();
    Call<LibraryResponse> call = service.searchLibrary(
        USERNAME, QUERY, QUERY_TYPE, TYPE, SORT, PAGE, PAGE_SIZE);
    call.enqueue(callback);
  }

}
