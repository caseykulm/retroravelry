package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.auth.AuthenticationHeaderProvider
import com.caseykulm.retroravelry.auth.OAuth2Authenticator
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
    val ravelryClient: RavelryClient by lazy { RavelryClient(oAuth2Authenticator, okhttpClient, server.url("/")) }
    private val okhttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    private val authenticationHeaderProvider: AuthenticationHeaderProvider by lazy {
        object : AuthenticationHeaderProvider {
            override fun getAuthorizationHeaderValue(request: Request): String {
                return "Bearer $MOCK_ACCESS_TOKEN"
            }
        }
    }
    private val oAuth2Authenticator: OAuth2Authenticator by lazy {
        object : OAuth2Authenticator {
            override val authHeaderProvider: AuthenticationHeaderProvider
                get() = authenticationHeaderProvider

            override fun isExpired(): Boolean {
                return false
            }

            override fun refresh(): Boolean {
                return true
            }

            override fun onRefreshFailed() {
                /* no-op */
            }
        }
    }
}
