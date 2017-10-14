package com.caseykulm.retroravelry.network.responses.patterns

import com.caseykulm.retroravelry.models.pattern.FullPattern

data class ShowPatternResponse(
    val pattern: FullPattern?
)
