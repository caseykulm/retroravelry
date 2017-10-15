package com.caseykulm.retroravelry.models.response.printing

import com.caseykulm.retroravelry.models.response.pattern.PatternSource

data class ListPrinting(
    val pattern_source: PatternSource?,
    val primary_source: Boolean?
)
