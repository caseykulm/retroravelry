package com.caseykulm.retroravelry.models.response.personalattribute

/**
 * Created by lorajones on 10/14/17.
 */
data class PersonalAttribute (
    val queued: Boolean,
    val bookmark_id: Int?,
    val in_library: Boolean,
    val favorited: Boolean
)