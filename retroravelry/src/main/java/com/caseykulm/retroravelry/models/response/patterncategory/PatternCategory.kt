package com.caseykulm.retroravelry.models.response.patterncategory

data class PatternCategory(
    val id: Int,
    val name: String?,
    val parent: PatternCategory?,
    val permalink: String?
)
