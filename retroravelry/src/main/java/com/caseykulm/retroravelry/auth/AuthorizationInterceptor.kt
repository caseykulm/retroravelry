package com.caseykulm.retroravelry.auth

import okhttp3.Interceptor
import okhttp3.Response

interface OAuth2Authenticator {
    /**
     * Should provide the value for the Authorization header.
     */
    val authHeaderProvider: AuthenticationHeaderProvider

    /**
     * Checks local cached timestamp to preemptively know to refresh rather than relying on a 401 to return.
     */
    fun isExpired(): Boolean

    /**
     * Does the work necessary to update the cached version of the Access Token, Refresh Token, and Timestamp
     */
    fun refresh(): Boolean

    /**
     * Notification so that we can bail appropriately
     */
    fun onRefreshFailed()
}

class AuthorizationInterceptor(private val oAuth2Manager: OAuth2Authenticator) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        checkIfExpired()

        val signedRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                oAuth2Manager.authHeaderProvider
                    .getAuthorizationHeaderValue(chain.request())
            )
            .build()
        val resp = chain.proceed(signedRequest)

        if (resp.code() == 401) {
            refresh()
        }

        return resp
    }

    private fun checkIfExpired() = when (oAuth2Manager.isExpired()) {
        true -> refresh()
        false -> { /* all good, proceed */ }
    }

    private fun refresh() = when (oAuth2Manager.refresh()) {
        true -> { /* successfully refreshed, all good, proceed */ }
        false -> {
            /* refresh failed, notify OAuth2Authenticator so it can bail appropriately */
            oAuth2Manager.onRefreshFailed()
        }
    }
}
