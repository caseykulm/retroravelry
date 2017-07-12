package com.caseykulm.retroravelry;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.concurrent.Callable;

public class RavelryAuthHelper {
  private final OAuth10aService service;
  private final SharedPrefPersister authPersister;

  public RavelryAuthHelper(OAuth10aService service, SharedPrefPersister authPersister) {
    this.service = service;
    this.authPersister = authPersister;
  }

  public Observable<OAuth1RequestToken> getRequestToken() {
    return Observable.defer(new Callable<ObservableSource<? extends OAuth1RequestToken>>() {
      @Override
      public ObservableSource<? extends OAuth1RequestToken> call() throws Exception {
        // Grab a request token.
        System.out.println("Fetching request token.");
        OAuth1RequestToken requestToken = service.getRequestToken();
        authPersister.saveRequestToken(requestToken);
        System.out.println("Got it ... ");
        System.out.println(requestToken.getToken());
        return Observable.just(requestToken);
      }
    });
  }

  public Function<OAuth1RequestToken, String> mapRequestTokenToAuthUrl() {
    return new Function<OAuth1RequestToken, String>() {
      @Override
      public String apply(OAuth1RequestToken oAuth1RequestToken) throws Exception {
        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        OAuth1RequestToken requestToken = authPersister.getRequestToken();
        String authUrl = service.getAuthorizationUrl(requestToken);
        System.out.println("Got the Authorization URL!");
        System.out.println(authUrl);
        return authUrl;
      }
    };
  }

  public Observable<OAuth1AccessToken> getAccessToken(final String oauthVerifier) {
    return Observable.defer(new Callable<ObservableSource<OAuth1AccessToken>>() {
      @Override
      public ObservableSource<OAuth1AccessToken> call() throws Exception {
        OAuth1AccessToken accessToken = authPersister.getAccessToken();
        if (accessToken == null) {
          System.out.println("Trading the Request Token for an Access Token...");
          OAuth1RequestToken requestToken = authPersister.getRequestToken();
          accessToken = service.getAccessToken(requestToken, oauthVerifier);
          authPersister.saveAccessToken(accessToken);
        }
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken
            + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
        System.out.println();
        return Observable.just(accessToken);
      }
    });
  }
}
