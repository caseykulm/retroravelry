package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.entities.Pattern

interface RavelryApi {
    fun searchPatterns(query: String, page: Int, pageSize: Int): List<Pattern>
}