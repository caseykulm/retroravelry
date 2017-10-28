package com.caseykulm.retroravelry

import com.caseykulm.oauthheader.Oauth1Client
import com.caseykulm.oauthheader.Oauth1Interceptor
import com.caseykulm.oauthheader.models.AccessTokenResponse
import com.caseykulm.oauthheader.models.OauthConsumer
import com.caseykulm.oauthheader.services.RavelryOauthService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class LiveClient {
  private val okhttpClient by lazy {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
  }
  private val oauthConsumer: OauthConsumer by lazy {
    OauthConsumer(
        oauthTestSecrets.consumerKey,
        oauthTestSecrets.consumerSecret,
        oauthTestSecrets.callbackUrl)
  }
  private val oauthService = RavelryOauthService()
  private val oauthClient: Oauth1Client by lazy {
    Oauth1Client(
        oauthConsumer,
        oauthService,
        okhttpClient)
  }
  // hardcoded
  private val accessTokenResponse: AccessTokenResponse by lazy {
    AccessTokenResponse(
        oauthTestSecrets.accessToken,
        oauthTestSecrets.accessTokenSecret)
  }
  private val oauthInterceptor: Oauth1Interceptor by lazy {
    Oauth1Interceptor(oauthClient, accessTokenResponse)
  }
  val ravelryClient: RavelryClient by lazy {
    RavelryClient(oauthTestSecrets.username, okhttpClient, oauthInterceptor)
  }

  private val oauthTestSecrets: OauthTestSecrets by lazy {
    parseJsonResourceFile("oauth_secrets.json", OauthTestSecrets::class.java)
  }

  private data class OauthTestSecrets(
      val consumerKey: String,
      val consumerSecret: String,
      val callbackUrl: String,
      val accessToken: String,
      val accessTokenSecret: String,
      val username: String
  )
}