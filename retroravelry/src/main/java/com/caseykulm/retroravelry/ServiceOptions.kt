package com.caseykulm.retroravelry

import okhttp3.Interceptor

data class ServiceOptions(
        val basicAuthUsername: String?,
        val basicAuthPassword: String?,
        val networkInterceptors: List<Interceptor>?) {

    companion object {
        val none = ServiceOptions(null, null, null)
    }
}