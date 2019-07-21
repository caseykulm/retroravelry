package com.caseykulm.retroravelry.models.response

data class Paginator (
    val last_page: Int,
    val page: Int,
    val page_count: Int,
    val page_size: Int,
    val results: Int
)
