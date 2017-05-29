package com.caseykulm.retroravelry;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;

public class RavelryOAuthApi extends DefaultApi10a {
  private static final String AUTHORIZE_URL = "https://www.ravelry.com/oauth/authorize?oauth_token=%s";

  protected RavelryOAuthApi() {
  }

  private static class InstanceHolder {
    private static final RavelryOAuthApi INSTANCE = new RavelryOAuthApi();
  }

  public static RavelryOAuthApi instance() {
    return InstanceHolder.INSTANCE;
  }

  @Override
  public String getRequestTokenEndpoint() {
    return "https://www.ravelry.com/oauth/request_token";
  }

  @Override
  public String getAccessTokenEndpoint() {
    return "https://www.ravelry.com/oauth/access_token";
  }

  @Override
  public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
    return String.format(AUTHORIZE_URL, requestToken.getToken());
  }
}
