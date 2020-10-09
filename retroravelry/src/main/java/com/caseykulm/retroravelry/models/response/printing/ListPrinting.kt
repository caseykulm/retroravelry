package com.caseykulm.retroravelry.models.response.printing

import com.caseykulm.retroravelry.models.response.pattern.PatternSource
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListPrinting(
    @Json(name = "pattern_source") val patternSource: PatternSource?,
    @Json(name = "primary_source") val primarySource: Boolean?
)
