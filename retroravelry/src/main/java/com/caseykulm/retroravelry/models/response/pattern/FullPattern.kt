package com.caseykulm.retroravelry.models.response.pattern

import com.caseykulm.retroravelry.models.response.craft.ListCraft
import com.caseykulm.retroravelry.models.response.needles.PatternNeedleSize
import com.caseykulm.retroravelry.models.response.pack.FullPack
import com.caseykulm.retroravelry.models.response.patternattribute.PatternAttribute
import com.caseykulm.retroravelry.models.response.patternauthor.PatternAuthor
import com.caseykulm.retroravelry.models.response.patterncategory.PatternCategory
import com.caseykulm.retroravelry.models.response.personalattribute.PersonalAttribute
import com.caseykulm.retroravelry.models.response.photo.Photo
import com.caseykulm.retroravelry.models.response.printing.ListPrinting
import com.caseykulm.retroravelry.models.response.yarnweight.ListYarnWeight
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullPattern(
    @Json(name = "comments_count") val commentsCount: Int?,
    val craft: ListCraft?,
    val currency: String?,
    @Json(name = "currency_symbol") val currencySymbol: String?,
    @Json(name = "difficulty_average") val difficultyAverage: Float?,
    @Json(name = "difficulty_count") val difficultyCount: Int?,
    val downloadable: Boolean?,
    @Json(name = "favorites_count") val favoritesCount: Int?,
    val free: Boolean?,
    val gauge: String?,
    @Json(name = "gauge_description") val gaugeDescription: String?,
    @Json(name = "gauge_divisor") val gaugeDivisor: String?,
    @Json(name = "gauge_pattern") val gaugePattern: String?,
    val id: Int,
    val name: String?,
    @Json(name = "notes_html") val notesHtml: String?,
    val notes: String?,
    val packs: List<FullPack>?,
    @Json(name = "pattern_attributes") val patternAttributes: List<PatternAttribute>?,
    @Json(name = "pattern_author") val patternAuthor: PatternAuthor?,
    @Json(name = "pattern_categories") val patternCategories: List<PatternCategory>?,
    @Json(name = "pattern_needle_sizes") val patternNeedleSizes: List<PatternNeedleSize>?,
    @Json(name = "pdf_in_library") val pdfInLibrary: Boolean?,
    @Json(name = "pdf_url") val pdfUrl: String?,
    val permalink: String?,
    @Json(name = "personal_attributes") val personalAttributes: PersonalAttribute?,
    val photos: List<Photo>?,
    val price: String?,
    val printings: List<ListPrinting>?,
    @Json(name = "product_id") val productId: Int?,
    @Json(name = "projects_count") val projectsCount: Int?,
    // TODO Parse this into a recognized date format, currently returning YYYY/MM/DD instead of standard YYYY-MM-DD
    val published: String?,
    @Json(name = "queued_projects_count") val queuedProjectsCount: Int?,
    @Json(name = "rating_average") val ratingAverage: Float?,
    @Json(name = "rating_count") val ratingCount: Int?,
    @Json(name = "ravelry_download") val ravelryDownload: Boolean?,
    @Json(name = "row_gauge") val rowGauge: String?,
    @Json(name = "sizes_available") val sizesAvailable: String?,
    val url: String?,
    @Json(name = "volumes_in_library") val volumesInLibrary: List<Int>?,
    val yardage: Int?,
    @Json(name = "yardage_description") val yardageDescription: String?,
    @Json(name = "yardage_max") val yardageMax: Int?,
    @Json(name = "yarn_weight") val yarnWeight: ListYarnWeight?,
    @Json(name = "yarn_weight_description") val yarnWeightDescription: String?
)
