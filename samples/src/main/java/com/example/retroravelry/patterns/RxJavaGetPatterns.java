package com.example.retroravelry.patterns;

import com.caseykulm.retroravelry.RxRetroRavelryService;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import com.example.retroravelry.ServiceFactory;
import rx.Single;
import rx.Subscription;
import rx.functions.Action1;

public class RxJavaGetPatterns extends BaseGetPatterns {

  private Action1<SearchPatternsResponse> success = new Action1<SearchPatternsResponse>() {
    public void call(SearchPatternsResponse response) {
      printPatterns(response);
    }
  };

  private Action1<Throwable> failure = new Action1<Throwable>() {
    public void call(Throwable throwable) {
      printErrors(throwable);
    }
  };

  @Override void getPatterns() {
    RxRetroRavelryService service = ServiceFactory.newRxService();
    Single<SearchPatternsResponse> call = service.searchPatterns("", 0, 10, false);
    Subscription subscription = call.subscribe(success, failure);
    // need to kill this subscription when done
  }

}
