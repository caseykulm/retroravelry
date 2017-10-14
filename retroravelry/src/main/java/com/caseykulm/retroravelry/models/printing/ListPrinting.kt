package com.caseykulm.retroravelry.models.printing

import com.caseykulm.retroravelry.models.pattern.PatternSource

data class ListPrinting(
    val pattern_source: PatternSource?,
    val primary_source: Boolean?
)
