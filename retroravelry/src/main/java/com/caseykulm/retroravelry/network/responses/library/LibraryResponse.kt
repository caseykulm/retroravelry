package com.caseykulm.retroravelry.network.responses.library

import com.caseykulm.retroravelry.models.response.Paginator
import com.caseykulm.retroravelry.models.response.volume.ListVolume
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LibraryResponse(
    val paginator: Paginator?,
    val volumes: List<ListVolume>?
)
