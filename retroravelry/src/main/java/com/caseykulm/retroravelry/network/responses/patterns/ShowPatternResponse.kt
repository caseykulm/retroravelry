package com.caseykulm.retroravelry.network.responses.patterns

import com.caseykulm.retroravelry.models.response.pattern.FullPattern
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShowPatternResponse(
    val pattern: FullPattern?
)
