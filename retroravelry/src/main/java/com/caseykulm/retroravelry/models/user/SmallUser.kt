package com.caseykulm.retroravelry.models.user

data class SmallUser(
    val id: Int,
    val large_photo_url: String?,
    val photo_url: String?,
    val small_photo_url: String?,
    val tiny_photo_url: String?,
    val username: String?
)
