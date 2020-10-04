package com.caseykulm.retroravelry.network.responses.user

import com.caseykulm.retroravelry.models.response.user.SmallUser
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrentUserResponse(
    @Json(name = "user") val smallUser: SmallUser
)
