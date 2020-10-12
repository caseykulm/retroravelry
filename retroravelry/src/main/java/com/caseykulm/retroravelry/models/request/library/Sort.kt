package com.caseykulm.retroravelry.models.request.library

import com.squareup.moshi.Json

/**
 * Created by lorajones on 10/15/17.
 */
enum class Sort {
    @Json(name = "title") Title,
    @Json(name = "added") Added,
    @Json(name = "published") Published,
    @Json(name = "author") Author
}
