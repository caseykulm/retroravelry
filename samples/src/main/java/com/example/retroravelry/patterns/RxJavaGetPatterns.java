package com.example.retroravelry.patterns;

import com.caseykulm.retroravelry.RxRetroRavelryService;
import com.caseykulm.retroravelry.RxServiceFactory;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxJavaGetPatterns extends BaseGetPatterns {
  private Consumer<SearchPatternsResponse> success = new Consumer<SearchPatternsResponse>() {
    public void accept(@NonNull SearchPatternsResponse response) throws Exception {
      printPatterns(response);
    }
  };

  private Consumer<Throwable> failure = new Consumer<Throwable>() {
    public void accept(@NonNull Throwable throwable) throws Exception {
      printErrors(throwable);
    }
  };

  @Override void getPatterns() {
    RxRetroRavelryService service = RxServiceFactory.newRxService();
    Single<SearchPatternsResponse> call = service.searchPatterns("", 0, 10, false);
    Disposable disposable = call.subscribe(success, failure);
    // need to kill this subscription when done
  }
}
