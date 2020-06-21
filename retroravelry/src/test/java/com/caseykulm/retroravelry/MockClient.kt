package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.auth.AuthProvider
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer

val MOCK_USER_NAME = "rumpletestuser"
val MOCK_ACCESS_TOKEN = "mockAccessToken"
val MOCK_ACCESS_TOKEN_SECRET = "mockAccessTokenSecret"
val MOCK_CONSUMER_KEY = "mockConsumerKey"
val MOCK_CONSUMER_SECRET = "mockConsumerSecret"
val MOCK_CALLBACK_URL = "https://example.com/oauth"

class MockClient {
  var server: MockWebServer = MockWebServer()
  val ravelryClient: RavelryClient by lazy { RavelryClient(authProvider, okhttpClient, server.url("/")) }
  private val okhttpClient by lazy {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
  }
  private val authProvider: AuthProvider by lazy {
    object : AuthProvider {
      override fun getAuthorizationHeaderValue(request: Request): String {
        return "Bearer $MOCK_ACCESS_TOKEN"
      }
    }
  }
}