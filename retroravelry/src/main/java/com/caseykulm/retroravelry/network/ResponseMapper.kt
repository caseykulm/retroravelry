package com.caseykulm.retroravelry.network

import com.caseykulm.retroravelry.entities.Pattern
import com.caseykulm.retroravelry.entities.Stash
import com.caseykulm.retroravelry.models.pattern.ListPattern

class ResponseMapper {
    fun toPatternEntity(listPattern: ListPattern): Pattern {
        val name = listPattern.name ?: "no name"
        val imageUrl = listPattern.first_photo?.medium_url ?: ""
        return Pattern(name, imageUrl)
    }
}