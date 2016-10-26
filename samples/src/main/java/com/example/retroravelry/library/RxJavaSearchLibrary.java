package com.example.retroravelry.library;

import com.caseykulm.retroravelry.RxRetroRavelryAuthService;
import com.caseykulm.retroravelry.responses.library.LibraryResponse;
import com.example.retroravelry.ServiceFactory;
import rx.Subscription;
import rx.functions.Action1;

public class RxJavaSearchLibrary extends BaseSearchLibrary {

  private Action1<LibraryResponse> success = new Action1<LibraryResponse>() {
    public void call(LibraryResponse libraryResponse) {
      printLibrary(libraryResponse);
    }
  };

  private Action1<Throwable> failure = new Action1<Throwable>() {
    public void call(Throwable throwable) {
      printErrors(throwable);
    }
  };

  @Override void searchLibrary() {
    RxRetroRavelryAuthService service = ServiceFactory.newRxAuthService();
    Subscription subscription = service.searchLibrary(
        USERNAME, QUERY, QUERY_TYPE, TYPE, SORT, PAGE, PAGE_SIZE)
      .subscribe(success, failure);
    // need to kill this subscription when done
  }

}
