package com.caseykulm.retroravelry;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;

public interface SharedPrefPersister {
  OAuth1AccessToken getAccessToken();
  void saveAccessToken(OAuth1AccessToken accessToken);
  String getKeyAuthorizationUrl();
  void saveAuthorizationUrl(String authUrl);
  OAuth1RequestToken getRequestToken();
  void saveRequestToken(OAuth1RequestToken requestToken);
}

