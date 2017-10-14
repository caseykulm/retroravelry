package com.caseykulm.retroravelry.models.pattern

import com.caseykulm.retroravelry.models.craft.ListCraft
import com.caseykulm.retroravelry.models.needles.PatternNeedleSize
import com.caseykulm.retroravelry.models.pack.FullPack
import com.caseykulm.retroravelry.models.patternattribute.PatternAttribute
import com.caseykulm.retroravelry.models.patternauthor.PatternAuthor
import com.caseykulm.retroravelry.models.patterncategory.PatternCategory
import com.caseykulm.retroravelry.models.personalattribute.PersonalAttribute
import com.caseykulm.retroravelry.models.photo.Photo
import com.caseykulm.retroravelry.models.printing.ListPrinting
import com.caseykulm.retroravelry.models.yarnweight.ListYarnWeight
import java.util.Date

data class FullPattern(
    val comments_count: Int,
    val craft: ListCraft?,
    val currency: String?,
    val currency_symbol: String?,
    val difficulty_average: Float,
    val difficulty_count: Int,
    val downloadable: Boolean,
    val favorites_count: Int,
    val free: Boolean,
    val gauge: String?,
    val gauge_description: String?,
    val gauge_divisor: String?,
    val gauge_pattern: String?,
    val id: Int,
    val name: String?,
    val notes_html: String?,
    val notes: String?,
    val packs: List<FullPack>?,
    val pattern_attributes: List<PatternAttribute>?,
    val pattern_author: PatternAuthor?,
    val pattern_categories: List<PatternCategory>?,
    val pattern_needle_sizes: List<PatternNeedleSize>?,
    val pdf_in_library: Boolean,
    val pdf_url: String?,
    val permalink: String?,
    val personal_attributes: PersonalAttribute?,
    val photos: List<Photo>?,
    val price: String?,
    val printings: List<ListPrinting>?,
    val product_id: Int,
    val projects_count: Int,
    //TODO Parse this into a recognized date format, currently returning YYYY/MM/DD instead of standard YYYY-MM-DD
    val published: String?,
    val queued_projects_count: Int,
    val rating_average: Float,
    val rating_count: Int,
    val ravelry_download: Boolean,
    val row_gauge: String?,
    val sizes_available: String?,
    val url: String?,
    val volumes_in_library: List<Int>?,
    val yardage: Int,
    val yardage_description: String?,
    val yardage_max: Int,
    val yarn_weight: ListYarnWeight?,
    val yarn_weight_description: String?
)
