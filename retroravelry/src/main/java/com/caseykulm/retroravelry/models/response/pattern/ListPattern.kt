package com.caseykulm.retroravelry.models.response.pattern

import com.caseykulm.retroravelry.models.response.patternauthor.PatternAuthor
import com.caseykulm.retroravelry.models.response.personalattribute.PersonalAttribute
import com.caseykulm.retroravelry.models.response.photo.Photo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListPattern(
    val designer: PatternAuthor?,
    @Json(name = "first_photo") val firstPhoto: Photo?,
    val free: Boolean,
    val id: Int,
    val name: String?,
    @Json(name = "pattern_author") val patternAuthor: PatternAuthor?,
    val permalink: String?,
    @Json(name = "personal_attributes") val personalAttributes: PersonalAttribute?
)
