package com.caseykulm.retroravelry.models

import com.squareup.moshi.Json

data class Paginator (
        @Json(name="last_page") val lastPage: Int,
        val page: Int,
        @Json(name="page_count") val pageCount: Int,
        @Json(name="page_size") val pageSize: Int,
        val results: Int
)
