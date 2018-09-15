package com.caseykulm.retroravelry.models.response.pattern

import com.caseykulm.retroravelry.models.response.patternauthor.PatternAuthor
import com.caseykulm.retroravelry.models.response.personalattribute.PersonalAttribute
import com.caseykulm.retroravelry.models.response.photo.Photo

data class ListPattern(
    val designer: PatternAuthor?,
    val first_photo: Photo?,
    val free: Boolean,
    val id: Int,
    val name: String?,
    val pattern_author: PatternAuthor?,
    val permalink: String?,
    val personal_attributes: PersonalAttribute?
)
