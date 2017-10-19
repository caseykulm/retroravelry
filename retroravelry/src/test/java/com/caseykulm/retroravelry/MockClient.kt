package com.caseykulm.retroravelry

import com.caseykulm.oauthheader.Oauth1Client
import com.caseykulm.oauthheader.Oauth1Interceptor
import com.caseykulm.oauthheader.models.AccessTokenResponse
import com.caseykulm.oauthheader.models.OauthConsumer
import com.caseykulm.oauthheader.services.RavelryOauthService
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer

val MOCK_USER_NAME = "rumpletestuser"
val MOCK_ACCESS_TOKEN = "mockAccessToken"
val MOCK_ACCESS_TOKEN_SECRET = "mockAccessTokenSecret"
val MOCK_CONSUMER_KEY = "mockConsumerKey"
val MOCK_CONSUMER_SECRET = "mockConsumerSecret"
val MOCK_CALLBACK_URL = "https://example.com/oauth"

class MockClient {
  var server: MockWebServer = MockWebServer()
  val ravelryClient: RavelryClient by lazy {
    RavelryClient(MOCK_USER_NAME, okhttpClient, oauthInterceptor, server.url("/"))
  }
  private val okhttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .build()
  }
  private val oauthInterceptor: Oauth1Interceptor by lazy {
    Oauth1Interceptor(oauthClient, accessTokenResponse)
  }
  private val oauthClient: Oauth1Client by lazy {
    Oauth1Client(
        oauthConsumer,
        oauthService,
        okhttpClient)
  }
  // hardcoded
  private val accessTokenResponse: AccessTokenResponse by lazy {
    AccessTokenResponse(MOCK_ACCESS_TOKEN,MOCK_ACCESS_TOKEN_SECRET)
  }
  private val oauthConsumer: OauthConsumer by lazy {
    OauthConsumer(MOCK_CONSUMER_KEY, MOCK_CONSUMER_SECRET, MOCK_CALLBACK_URL)
  }
  private val oauthService = RavelryOauthService()
}