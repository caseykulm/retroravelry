package com.caseykulm.retroravelry.network.responses.photos

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowPhotoSizesResponse(
    val id: String?
)
