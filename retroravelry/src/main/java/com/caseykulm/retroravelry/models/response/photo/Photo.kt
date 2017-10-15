package com.caseykulm.retroravelry.models.response.photo

data class Photo(
    val id: String?,
    val medium_url: String?,
    val small_url: String?,
    val square_url: String?,
    val thumbnail_url: String?,
    val sort_order: String?,
    val x_offset: Int,
    val y_offset: Int
)
