package com.caseykulm.retroravelry.models.response.yarnweight

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListYarnWeight(
    val id: Int,
    val name: String
)
