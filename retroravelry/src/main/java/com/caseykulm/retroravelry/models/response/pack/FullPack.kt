package com.caseykulm.retroravelry.models.response.pack

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullPack(
    @Json(name = "color_family_id") val colorFamilyId: String?,
    val colorway: String?,
    @Json(name = "dye_lot") val dyeLot: String?,
    val id: Int?,
    @Json(name = "personal_name") val personalName: String?,
    @Json(name = "project_id") val projectId: Int?,
    @Json(name = "shop_id") val shopId: Int?,
    @Json(name = "shop_name") val shopName: String?,
    val skeins: String?,
    @Json(name = "stash_id") val stashId: Int?,
    @Json(name = "yarn_id") val yarnId: Int?,
    @Json(name = "yarn_name") val yarnName: String?
)
