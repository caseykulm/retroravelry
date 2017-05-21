package com.caseykulm.retroravelry.models.patternauthor

import com.caseykulm.retroravelry.models.user.SmallUser

data class PatternAuthor(
    val favorites_count: Int,
    val id: Int,
    val name: String?,
    val patterns_count: Int,
    val permalink: String?,
    val users: List<SmallUser>?
)
