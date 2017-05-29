package com.example;

import com.caseykulm.retroravelry.RavelryOAuthApi;
import com.caseykulm.retroravelry.ServiceFactory;
import com.caseykulm.retroravelry.ServiceOptions;
import com.caseykulm.retroravelry.responses.patterns.SearchPatternsResponse;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuthService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    final OAuth10aService service = new ServiceBuilder()
        .apiKey("65C43947D5EE17D3ECDF")
        .apiSecret("dc3cdc87JtP7IE93EX1uqXwg8ECSsqe1f3mczT0W")
        .callback("your://redirecturi")
        .build(RavelryOAuthApi.instance());
    blah(service);

    //Call<SearchPatternsResponse> call = ServiceFactory
    //    .newService(serviceOptions)
    //    .searchPatterns("purple", 0, 10, false);
    //Response<SearchPatternsResponse> response = call.execute();
    //
    //assertTrue("Failed with code: " + response.code(), response.isSuccessful());
    //
    //SearchPatternsResponse searchPatternsResponse = response.body();
    //System.out.println("numPages: " + searchPatternsResponse.getPaginator().getPageSize());
  }

  private void blah(OAuth10aService service) throws Exception {
    final Scanner in = new Scanner(System.in);

    // Grab a request token.
    System.out.println("Fetching request token.");
    final OAuth1RequestToken requestToken = service.getRequestToken();
    System.out.println("Got it ... ");
    System.out.println(requestToken.getToken());

    // Obtain the Authorization URL
    System.out.println("Fetching the Authorization URL...");
    final String authorizationUrl = service.getAuthorizationUrl(requestToken);
    System.out.println("Got the Authorization URL!");
    System.out.println("Now go and authorize ScribeJava here:");
    System.out.println(authorizationUrl);
    System.out.println("And paste the authorization code here");
    System.out.print(">>");
    final String oauthVerifier = in.nextLine();
    System.out.println();

    // Trade the Request Token and Verfier for the Access Token
    System.out.println("Trading the Request Token for an Access Token...");
    final OAuth1AccessToken accessToken = service.getAccessToken(requestToken, oauthVerifier);
    System.out.println("Got the Access Token!");
    System.out.println("(if your curious it looks like this: " + accessToken
        + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
    System.out.println();
  }
}
