package com.caseykulm.retroravelry.models.response.craft

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListCraft(
    val id: Int,
    val name: String?,
    val permalink: String?
)
