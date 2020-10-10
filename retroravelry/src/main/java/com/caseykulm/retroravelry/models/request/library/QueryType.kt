package com.caseykulm.retroravelry.models.request.library

import com.squareup.moshi.Json

enum class QueryType {
    @Json(name = "patterns") Patterns,
    @Json(name = "tags") Tags
}
