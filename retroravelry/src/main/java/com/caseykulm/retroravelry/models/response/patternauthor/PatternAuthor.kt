package com.caseykulm.retroravelry.models.response.patternauthor

import com.caseykulm.retroravelry.models.response.user.SmallUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PatternAuthor(
    @Json(name = "favorites_count") val favoritesCount: Int,
    val id: Int,
    val name: String?,
    @Json(name = "patterns_count") val patternsCount: Int,
    val permalink: String?,
    val users: List<SmallUser>?
)
