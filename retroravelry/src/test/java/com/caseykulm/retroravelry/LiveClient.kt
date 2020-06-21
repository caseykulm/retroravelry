package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.auth.AuthProvider
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
  private val authProvider: AuthProvider by lazy {
    object : AuthProvider {
      override fun getAuthorizationHeaderValue(request: Request): String {
        return "Bearer ${oauthTestSecrets.accessToken}"
      }
    }
  }

  val ravelryClient: RavelryClient by lazy { RavelryClient(authProvider, okhttpClient) }

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