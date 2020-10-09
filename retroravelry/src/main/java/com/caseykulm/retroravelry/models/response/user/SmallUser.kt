package com.caseykulm.retroravelry.models.response.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SmallUser(
    val id: Int,
    @Json(name = "large_photo_url") val largePhotoUrl: String?,
    @Json(name = "photo_url") val photoUrl: String?,
    @Json(name = "small_photo_url") val smallPhotoUrl: String?,
    @Json(name = "tiny_photo_url") val tinyPhotoUrl: String?,
    val username: String?
)
