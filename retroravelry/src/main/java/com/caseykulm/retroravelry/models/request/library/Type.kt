package com.caseykulm.retroravelry.models.request.library

import com.squareup.moshi.Json

/**
 * Created by lorajones on 10/15/17.
 */
enum class Type {
    @Json(name = "book") Book,
    @Json(name = "magazine") Magazine,
    @Json(name = "booklet") Booklet,
    @Json(name = "pattern") Pattern,
    @Json(name = "pdf") Pdf
}
