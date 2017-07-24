package com.caseykulm.retroravelry.utils;

import static org.junit.Assert.*;

/*
 * Copyright (C) 2015 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.ByteString;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class Oauth1SigningInterceptorTest {
  @Test
  public void litmus() throws IOException {
    // Data from https://dev.twitter.com/oauth/overview/authorizing-requests.

    Random notRandom = new Random() {
      @Override public void nextBytes(byte[] bytes) {
        if (bytes.length != 32) throw new AssertionError();
        ByteString hex = ByteString.decodeBase64("kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4c+g");
        byte[] nonce = hex.toByteArray();
        System.arraycopy(nonce, 0, bytes, 0, nonce.length);
      }
    };

    Oauth1SigningInterceptor.Clock clock = mock(Oauth1SigningInterceptor.Clock.class);
    when(clock.millis()).thenReturn("1318622958");

    Oauth1SigningInterceptor oauth1 = new Oauth1SigningInterceptor.Builder()
        .consumerKey("xvz1evFS4wEEPTGEFPHBog")
        .consumerSecret("kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw")
        .accessToken("370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb")
        .accessSecret("LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE")
        .random(notRandom)
        .clock(clock)
        .build();

    RequestBody body = new FormBody.Builder()
        .add("status", "Hello Ladies + Gentlemen, a signed OAuth request!")
        .build();
    Request request = new Request.Builder()
        .url("https://api.twitter.com/1/statuses/update.json?include_entities=true")
        .post(body)
        .build();

    Request signed = oauth1.signRequest(request);
    assertThat(signed.header("Authorization")).isEqualTo("OAuth "
        + "oauth_consumer_key=\"xvz1evFS4wEEPTGEFPHBog\", "
        + "oauth_nonce=\"kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg\", "
        + "oauth_signature=\"tnnArxj06cWHq44gCs1OSKk%2FjLY%3D\", "
        + "oauth_signature_method=\"HMAC-SHA1\", "
        + "oauth_timestamp=\"1318622958\", "
        + "oauth_token=\"370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb\", "
        + "oauth_version=\"1.0\"");
  }

  //@Test
  //public void ravelryOAuthGen() throws Exception {
  //  String oAuthNonce = Oauth1SigningInterceptor.getOauthNonce(new SecureRandom());
  //  String oAuthCallback = "oob";
  //  String oAuthConsumerKey = Oauth1SigningInterceptor.getOauthConsumerKey("65C43947D5EE17D3ECDF");
  //  String oAuthTimestamp = Oauth1SigningInterceptor.getOauthTimestamp(new Oauth1SigningInterceptor.Clock());
  //  String oAuthSignatureMethod = Oauth1SigningInterceptor.getOauthSignatureMethod();
  //  String oAuthVersion = Oauth1SigningInterceptor.getOAuthVersion();
  //
  //  Oauth1SigningInterceptor oauth1 = new Oauth1SigningInterceptor.Builder()
  //      .consumerKey("65C43947D5EE17D3ECDF")
  //      .consumerSecret("dc3cdc87JtP7IE93EX1uqXwg8ECSsqe1f3mczT0W")
  //      .accessToken("")
  //      .accessSecret("")
  //      .build();
  //
  //  RequestBody body = new FormBody.Builder()
  //      .add("status", "Hello Ladies + Gentlemen, a signed OAuth request!")
  //      .build();
  //  Request request = new Request.Builder()
  //      .url("https://api.ravelry.com/current_user.json")
  //      .post(body)
  //      .build();
  //
  //  Request signed = oauth1.signRequest(request);
  //  assertThat(signed.header("Authorization")).isEqualTo("OAuth "
  //      + "oauth_consumer_key=\"65C43947D5EE17D3ECDF\", "
  //      + "oauth_nonce=\"" + oAuthNonce + "\", "
  //      + "oauth_signature=\"" + oAuthSignature + "\", "
  //      + "oauth_signature_method=\"" + oAuthSignatureMethod + "\", "
  //      + "oauth_timestamp=\"" + oAuthTimestamp + "\", "
  //      + "oauth_token=\""+oAuthToken+"\", "
  //      + "oauth_version=\""+oAuthVersion+"\"");
  //}

  @Test
  public void generateTempCredentialsRavelry() throws IOException {
    String oAuthNonce = Oauth1SigningInterceptor.getOauthNonce(new SecureRandom());
    String oAuthCallback = "oob";
    String oAuthConsumerKey = Oauth1SigningInterceptor.getOauthConsumerKey("65C43947D5EE17D3ECDF");
    String oAuthTimestamp = Oauth1SigningInterceptor.getOauthTimestamp(new Oauth1SigningInterceptor.Clock());
    String oAuthSignatureMethod = Oauth1SigningInterceptor.getOauthSignatureMethod();
    String oAuthVersion = Oauth1SigningInterceptor.getOAuthVersion();
    //String oAuthSignature = Oauth1SigningInterceptor.getOAuthSignature(
    //    new HttpUrl.Builder()
    //        .scheme("https")
    //        .host("www.ravelry.com")
    //        .encodedPath("oauth/request_token")
    //        .build(),
    //    null,
    //    "POST",
    //    "dc3cdc87JtP7IE93EX1uqXwg8ECSsqe1f3mczT0W",
    //
    //    );

    StringBuilder authHeaderBuilder = new StringBuilder("OAuth ");
    authHeaderBuilder.append("oauth_nonce=\"").append(oAuthNonce).append("\", ");
    authHeaderBuilder.append("oauth_callback=\"").append(oAuthCallback).append("\", ");
    authHeaderBuilder.append("oauth_consumer_key=\"").append(oAuthConsumerKey).append("\", ");
    authHeaderBuilder.append("oauth_timestamp=\"").append(oAuthTimestamp).append("\", ");
    authHeaderBuilder.append("oauth_signature_method=\"").append(oAuthSignatureMethod).append("\", ");
    authHeaderBuilder.append("oauth_version=\"").append(oAuthVersion).append("\"");

    //HttpUrl httpUrl = new HttpUrl.Builder()
    //    .scheme("https")
    //    .host("www.ravelry.com")
    //    .encodedPath("oauth/request_token")
    //    .addQueryParameter("scope", "profile-only")
    //    .build();
    //
    //Request request = new Request.Builder()
    //    .url(httpUrl)
    //    .addHeader("Authorization", authHeaderBuilder.toString())
    //    .build();
    //
    ////authHeaderBuilder.append("oauth_signature=\"").append(oAuthSignature).append("\", ");
    //
    //OkHttpClient okHttpClient = new OkHttpClient.Builder()
    //    .addNetworkInterceptor(new Interceptor() {
    //      @Override public Response intercept(Chain chain) throws IOException {
    //        Request req = chain.request();
    //        System.out.println(req.toString());
    //        Response resp = chain.proceed(req);
    //        System.out.println(resp.toString());
    //        return resp;
    //      }
    //    })
    //    .build();
    //
    //okHttpClient.newCall(request).execute();
  }
}