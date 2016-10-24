package com.example.retroravelry.patterns;

import com.caseykulm.retroravelry.RxRetroRavelryService;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import com.example.retroravelry.ServiceFactory;
import rx.Single;
import rx.functions.Action1;

public class RxJavaGetPatterns extends BaseGetPatterns {

  @Override void getPatterns() {
    RxRetroRavelryService service = ServiceFactory.newRxService();
    Single<SearchPatternsResponse> call = service.searchPatterns("", 0, 10, false);
    call.subscribe(new Action1<SearchPatternsResponse>() {
      public void call(SearchPatternsResponse response) {
        printPatterns(response);
      }
    }, new Action1<Throwable>() {
      public void call(Throwable throwable) {
        printErrors(throwable);
      }
    });
  }

}
