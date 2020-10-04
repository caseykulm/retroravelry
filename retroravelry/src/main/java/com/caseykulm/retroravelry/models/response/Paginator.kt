package com.caseykulm.retroravelry.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Paginator(
    @Json(name = "last_page") val lastPage: Int,
    val page: Int,
    @Json(name = "page_count") val pageCount: Int,
    @Json(name = "page_size") val pageSize: Int,
    val results: Int
)
