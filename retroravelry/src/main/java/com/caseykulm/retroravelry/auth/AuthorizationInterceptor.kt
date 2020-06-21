package com.caseykulm.retroravelry.auth

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val authProvider: AuthProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val signedRequest = chain.request().newBuilder()
                .addHeader("Authorization", authProvider.getAuthorizationHeaderValue(chain.request()))
                .build()
        return chain.proceed(signedRequest)
    }
}