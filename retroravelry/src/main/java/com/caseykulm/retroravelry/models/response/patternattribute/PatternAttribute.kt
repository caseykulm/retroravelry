package com.caseykulm.retroravelry.models.response.patternattribute

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatternAttribute(
    val id: Int,
    val permalink: String?
)
