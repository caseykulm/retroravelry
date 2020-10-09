package com.caseykulm.retroravelry.models.response.personalattribute

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by lorajones on 10/14/17.
 */
@JsonClass(generateAdapter = true)
data class PersonalAttribute(
    val queued: Boolean,
    @Json(name = "bookmark_id") val bookmarkId: Int?,
    @Json(name = "in_library") val inLibrary: Boolean,
    val favorited: Boolean
)
