package com.caseykulm.retroravelry.models.response.pack

import com.squareup.moshi.Json

data class FullPack(
    @com.squareup.moshi.Json(name="color_family_id") val colorFamilyId: String?,
    val colorway: String?,
    val dye_lot: String?,
    val id: Int?,
    val personal_name: String?,
    val project_id: Int?,
    val shop_id: Int?,
    val shop_name: String?,
    val skeins: String?,
    val stash_id: Int?,
    val yarn_id: Int?,
    val yarn_name: String?
)
