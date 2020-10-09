package com.caseykulm.retroravelry.auth

import okhttp3.Request

interface AuthenticationHeaderProvider {
    fun getAuthorizationHeaderValue(request: Request): String
}
