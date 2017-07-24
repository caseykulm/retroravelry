package com.caseykulm.retroravelry.utils

import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.Request
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.security.SecureRandom
import java.util.*

class OAuth1FormatterTest {
    lateinit var formatter: OAuth1Formatter

    @Before
    fun setUp() {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val random = SecureRandom()
        formatter = OAuth1Formatter("consumerKey", "consumerSecret", calendar, random)
    }

    @Test @Throws(Exception::class)
    fun generateTimestamp() {
        val timestamp = formatter.generateTimestamp()
    }

    @Test @Throws(Exception::class)
    fun generateAuthHeaderValue() {
        val authHeaderValue = formatter.generateAuthHeaderValue(
            "oob",
            "consumerkey",
            "noncey",
            "siggy",
            "123")

        val expectedHeaderValue = "OAuth oauth_callback=\"oob\","+
            "oauth_consumer_key=\"consumerkey\","+
            "oauth_nonce=\"noncey\","+
            "oauth_signature=\"siggy\","+
            "oauth_signature_method=\"HMAC-SHA1\","+
            "oauth_timestamp=\"123\","+
            "oauth_version=\"1.0\""

        assertEquals(expectedHeaderValue, authHeaderValue)
    }

    @Test @Throws(Exception::class)
    fun generateNonce() {
        val nonce = formatter.generateNonce()
    }

    @Test
    fun generateSignatureBaseString() {
        // Example input from https://tools.ietf.org/html/rfc5849#section-3.4.1.1

        val url = HttpUrl.Builder()
                .scheme("http")
                .host("example.com")
                .addPathSegment("request")
                .addQueryParameter("b5", "%3D%253D")
                .addQueryParameter("a3", "a")
                .addQueryParameter("c%40", "")
                .addQueryParameter("a2", "r%20b")
                .build()

        val formBody = FormBody.Builder()
                .add("c2", "")
                .add("a3", "2+q")
                .build();

        val authHeaderVal = """OAuth realm="Example",oauth_consumer_key="9djdj82h48djs9d2",oauth_token="kkk9d7dh3k39sjv7",oauth_signature_method="HMAC-SHA1",oauth_timestamp="137131201",oauth_nonce="7d8f3e4a",oauth_signature="bYT5CMsGcbgUdFHObYMEfcx6bsw%3D"""

        val request = Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", authHeaderVal)
                .post(formBody)
                .build()

        val oAuthMetaData = OAuth1Formatter.OAuthMetaData("oob",
                "9djdj82h48djs9d2",
                "7d8f3e4a",
                "137131201",
                "kkk9d7dh3k39sjv7")

        val actualBaseString = formatter.generateSignatureBaseString(
                request,
                oAuthMetaData)

        val expectedBaseString = "POST&http%3A%2F%2Fexample.com%2Frequest&a2%3Dr%2520b%26a3%3D2%2520q%26a3%3Da%26b5%3D%253D%25253D%26c%2540%3D%26c2%3D%26oauth_callback%3Doob%26oauth_consumer_key%3D9djdj82h48djs9d2%26oauth_nonce%3D7d8f3e4a%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D137131201%26oauth_token%3Dkkk9d7dh3k39sjv7%26oauth_version%3D1.0"

//        assertEquals(expectedBaseString, actualBaseString)
    }


}