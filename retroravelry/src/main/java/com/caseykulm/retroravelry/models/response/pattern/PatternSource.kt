package com.caseykulm.retroravelry.models.response.pattern

/**
 * Created by lorajones on 10/14/17.
 */

data class PatternSource (
    val out_of_print: Boolean?,
    val id: Int?,
    val name: String?,
    val patterns_count: Int?,
    val author: String?,
    val amazon_url: String?,
    val amazon_rating: Int?,
    val url: String?,
    val list_price: Int?,
    val price: Int?,
    val permalink: String?,
    val shelf_image_path: String?
)