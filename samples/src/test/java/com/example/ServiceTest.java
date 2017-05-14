package com.example;

import com.caseykulm.retroravelry.responses.patterns.ShowPatternResponse;
import com.example.retroravelry.ServiceFactory;
import org.junit.Test;
import retrofit2.Response;

public class ServiceTest {
  @Test
  public void serviceTest() throws Exception {
    Response<ShowPatternResponse> response = ServiceFactory
        .newService()
        .showPattern(123)
        .execute();
    ShowPatternResponse showPatternResponse = response.body();
    System.out.println("name: " + showPatternResponse.patterns().name());
  }
}
