package com.caseykulm.retroravelry.network.responses.patterns

import com.caseykulm.retroravelry.models.Paginator
import com.caseykulm.retroravelry.models.pattern.ListPattern

data class SearchPatternsResponse(
    val paginator: Paginator?,
    val patterns: List<ListPattern>?
)
