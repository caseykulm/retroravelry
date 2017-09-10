package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.entities.Pattern
import com.caseykulm.retroravelry.entities.Stash

interface RavelryApi {
  fun searchPatterns(query: String, page: Int, pageSize: Int): List<Pattern>
  fun getMyStashes(): List<Stash>
  fun getStashes(username: String): List<Stash>
}