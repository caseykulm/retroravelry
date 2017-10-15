package com.caseykulm.retroravelry.models.response.volume

import com.caseykulm.retroravelry.models.response.photo.Photo

data class ListVolume(
    val author_name: String?,
    val cover_image_size: Int?,
    val cover_image_url: String?,
    val first_photo: Photo?,
    val has_downloads: Boolean?,
    val id: Int,
    val pattern_id: Int?,
    val pattern_source_id: Int?,
    val patterns_count: Int?,
    val small_image_url: String?,
    val square_image_url: String?,
    val title: String?
)
