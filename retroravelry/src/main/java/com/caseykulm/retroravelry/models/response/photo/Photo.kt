package com.caseykulm.retroravelry.models.response.photo

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    val id: String?,
    @Json(name = "medium_url") val mediumUrl: String?,
    @Json(name = "small_url") val smallUrl: String?,
    @Json(name = "square_url") val squareUrl: String?,
    @Json(name = "thumbnail_url") val thumbnailUrl: String?,
    @Json(name = "sort_order") val sortOrder: String?,
    @Json(name = "x_offset") val xOffset: Int,
    @Json(name = "y_offset") val yOffset: Int
)
