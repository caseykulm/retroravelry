package com.caseykulm.retroravelry.models.response.needles

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by lorajones on 10/14/17.
 */
@JsonClass(generateAdapter = true)
data class PatternNeedleSize(
    val hook: String?,
    @Json(name = "pretty_metric") val prettyMetric: String?,
    val us: String?,
    @Json(name = "us_steel") val usSteel: String?,
    val knitting: Boolean,
    val metric: Double?,
    val name: String?,
    val crochet: Boolean,
    val id: Int?
)
