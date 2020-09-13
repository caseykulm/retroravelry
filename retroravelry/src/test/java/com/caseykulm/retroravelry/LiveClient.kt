package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.auth.AuthenticationHeaderProvider
import com.caseykulm.retroravelry.auth.OAuth2Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

class LiveClient {
  private val okhttpClient by lazy {
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
  }

  // hardcoded
  private val authenticationHeaderProvider: AuthenticationHeaderProvider by lazy {
    object : AuthenticationHeaderProvider {
      override fun getAuthorizationHeaderValue(request: Request): String {
        return "Bearer ${oauthTestSecrets.accessToken}"
      }
    }
  }
  private val oAuth2Authenticator: OAuth2Authenticator by lazy {
    object : OAuth2Authenticator {
      override val authHeaderProvider: AuthenticationHeaderProvider
        get() = this@LiveClient.authenticationHeaderProvider

      override fun isExpired(): Boolean {
        return false
      }

      override fun refresh(): Boolean {
        return false
      }

      override fun onRefreshFailed() {
        /* no-op */
      }
    }
  }

  val ravelryClient: RavelryClient by lazy { RavelryClient(oAuth2Authenticator, okhttpClient) }

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