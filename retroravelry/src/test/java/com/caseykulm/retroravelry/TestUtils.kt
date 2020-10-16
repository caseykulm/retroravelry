package com.caseykulm.retroravelry

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okio.Okio
import java.io.IOException
import java.io.InputStream
import java.util.Date

@Throws(IOException::class)
fun readFile(stream: InputStream): String {
    Okio.buffer(Okio.source(stream)).use { source -> return source.readUtf8() }
}

fun <T> Class<T>.readResourceFile(filename: String): String {
    val inputStream = classLoader.getResourceAsStream(filename)
    // TODO Report that this cannot find the file
    return readFile(inputStream)
}

@Throws(Exception::class)
fun <ResponseType> parseJsonResourceFile(filename: String, responseClass: Class<ResponseType>): ResponseType {
    val responseStr = responseClass.readResourceFile(filename)
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()
    val jsonAdapter = moshi.adapter<ResponseType>(responseClass)
    return jsonAdapter.fromJson(responseStr)!!
}
