package com.caseykulm.retroravelry.network.responses.patterns

import com.caseykulm.retroravelry.models.response.Paginator
import com.caseykulm.retroravelry.models.response.pattern.ListPattern

data class SearchPatternsResponse(
    val paginator: Paginator?,
    val patterns: List<ListPattern>?
)
