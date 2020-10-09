package com.caseykulm.retroravelry.models.response.pattern

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by lorajones on 10/14/17.
 */
@JsonClass(generateAdapter = true)
data class PatternSource(
    @Json(name = "out_of_print") val outOfPrint: Boolean?,
    val id: Int?,
    val name: String?,
    @Json(name = "patterns_count") val patternsCount: Int?,
    val author: String?,
    @Json(name = "amazon_url") val amazonUrl: String?,
    @Json(name = "amazon_rating") val amazonRating: Int?,
    val url: String?,
    @Json(name = "list_price") val listPrice: String?,
    val price: String?,
    val permalink: String?,
    @Json(name = "shelf_image_path") val shelfImagePath: String?
)
