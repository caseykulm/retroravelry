package com.caseykulm.retroravelry.models.response.patterncategory

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatternCategory(
    val id: Int,
    val name: String?,
    val parent: PatternCategory?,
    val permalink: String?
)
