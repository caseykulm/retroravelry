package com.caseykulm.retroravelry.models.pattern

import com.caseykulm.retroravelry.models.patternauthor.PatternAuthor
import com.caseykulm.retroravelry.models.photo.Photo

data class ListPattern(
    val designer: PatternAuthor?,
    val first_photo: Photo?,
    val free: Boolean,
    val id: Int,
    val name: String?,
    val pattern_author: PatternAuthor?,
    val permalink: String?,
    val personal_attributes: Map<String, String>?
)
