package com.caseykulm.retroravelry.models.response.volume

import com.caseykulm.retroravelry.models.response.photo.Photo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListVolume(
    @Json(name = "author_name") val authorName: String?,
    @Json(name = "cover_image_size") val coverImageSize: Int?,
    @Json(name = "cover_image_url") val coverImageUrl: String?,
    @Json(name = "first_photo") val firstPhoto: Photo?,
    @Json(name = "has_downloads") val hasDownloads: Boolean?,
    val id: Int,
    @Json(name = "pattern_id") val patternId: Int?,
    @Json(name = "pattern_source_id") val patternSourceId: Int?,
    @Json(name = "patterns_count") val patternsCount: Int?,
    @Json(name = "small_image_url") val smallImageUrl: String?,
    @Json(name = "square_image_url") val squareImageUrl: String?,
    val title: String?
)
