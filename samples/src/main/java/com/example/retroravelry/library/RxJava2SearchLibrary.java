package com.example.retroravelry.library;

import com.caseykulm.retroravelry.RxRetroRavelryAuthService;
import com.caseykulm.retroravelry.RxServiceFactory;
import com.caseykulm.retroravelry.responses.library.LibraryResponse;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxJava2SearchLibrary extends BaseSearchLibrary {

  private Consumer<LibraryResponse> success = new Consumer<LibraryResponse>() {
    public void accept(@NonNull LibraryResponse libraryResponse) throws Exception {
      printLibrary(libraryResponse);
    }
  };

  private Consumer<Throwable> failure = new Consumer<Throwable>() {
    public void accept(@NonNull Throwable throwable) throws Exception {
      printErrors(throwable);
    }
  };

  @Override void searchLibrary() {
    RxRetroRavelryAuthService service = RxServiceFactory.newRxAuthService();
    Disposable disposable = service.searchLibrary(
        USERNAME, QUERY, QUERY_TYPE, TYPE, SORT, PAGE, PAGE_SIZE)
      .subscribe(success, failure);
    // need to kill this subscription when done
  }

}
