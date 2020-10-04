package com.caseykulm.retroravelry.network.responses.patterns

import com.caseykulm.retroravelry.models.response.Paginator
import com.caseykulm.retroravelry.models.response.pattern.ListPattern
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchPatternsResponse(
    val paginator: Paginator?,
    val patterns: List<ListPattern>?
)
