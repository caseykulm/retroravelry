package com.caseykulm.retroravelry.auth

import okhttp3.Request

interface AuthProvider {
    fun getAuthorizationHeaderValue(request: Request): String
}