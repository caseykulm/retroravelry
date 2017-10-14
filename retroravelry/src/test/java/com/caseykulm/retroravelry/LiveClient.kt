package com.caseykulm.retroravelry

import com.caseykulm.oauthheader.Oauth1Client
import com.caseykulm.oauthheader.Oauth1Interceptor
import com.caseykulm.oauthheader.models.AccessTokenResponse
import com.caseykulm.oauthheader.models.OauthConsumer
import com.caseykulm.oauthheader.services.RavelryOauthService
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okio.Okio
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.io.IOException
import java.io.InputStream

@Throws(IOException::class)
fun readFile(stream: InputStream): String {
  Okio.buffer(Okio.source(stream)).use({ source -> return source.readUtf8() })
}

@Throws(Exception::class)
fun <ResponseType> parseJsonResourceFile(filename: String, responseClass: Class<ResponseType>): ResponseType {
  val inputStream = responseClass.classLoader.getResourceAsStream(filename)
  val responseStr = readFile(inputStream)
  val moshi = Moshi.Builder().build()
  val jsonAdapter = moshi.adapter<ResponseType>(responseClass)
  return jsonAdapter.fromJson(responseStr)!!
}

class LiveClient {
  private val okhttpClient = OkHttpClient.Builder().build()
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