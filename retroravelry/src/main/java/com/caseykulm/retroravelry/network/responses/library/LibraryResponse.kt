package com.caseykulm.retroravelry.network.responses.library

import com.caseykulm.retroravelry.models.Paginator
import com.caseykulm.retroravelry.models.volume.ListVolume

data class LibraryResponse(
    val paginator: Paginator?,
    val volumes: List<ListVolume>?
)