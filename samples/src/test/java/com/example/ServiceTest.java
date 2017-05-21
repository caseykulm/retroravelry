package com.example;

import com.caseykulm.retroravelry.ServiceFactory;
import com.caseykulm.retroravelry.ServiceOptions;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Interceptor;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertTrue;

public class ServiceTest {
  @Test
  public void serviceTest() throws Exception {
    Interceptor loggingInterceptor = new Interceptor() {
      @Override public okhttp3.Response intercept(Chain chain) throws IOException {
        System.out.println("requesting: " + chain.request().toString());
        okhttp3.Response response = chain.proceed(chain.request());
        System.out.println("response code: " + response.code());
        return response;
      }
    };
    List<Interceptor> networkInterceptors = new ArrayList<>();
    networkInterceptors.add(loggingInterceptor);
    ServiceOptions serviceOptions = new ServiceOptions(
        "USER",
        "PASS",
        networkInterceptors);
    Call<SearchPatternsResponse> call = ServiceFactory
        .newService(serviceOptions)
        .searchPatterns("purple", 0, 10, false);
    Response<SearchPatternsResponse> response = call.execute();

    assertTrue("Failed with code: " + response.code(), response.isSuccessful());

    SearchPatternsResponse searchPatternsResponse = response.body();
    System.out.println("numPages: " + searchPatternsResponse.getPaginator().getPageSize());
  }
}
