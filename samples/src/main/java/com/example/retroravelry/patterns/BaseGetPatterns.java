package com.example.retroravelry.patterns;

import com.caseykulm.retroravelry.models.pattern.ListPattern;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import java.util.List;

public abstract class BaseGetPatterns implements Runnable {

  public void run() {
    getPatterns();
  }

  abstract void getPatterns();

  protected void printPatterns(SearchPatternsResponse response) {
    System.out.println("Found patterns:");
    List<ListPattern> patterns = response.patterns();
    for (ListPattern pattern : patterns) {
      System.out.println(pattern.name());
    }
  }

  protected void printErrors(Throwable throwable) {
    System.out.println(throwable.getMessage());
  }
}
