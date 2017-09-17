package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.entities.Pattern
import com.caseykulm.retroravelry.entities.Stash
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import retrofit2.Call

interface RavelryApi {
  fun searchPatterns(query: String, page: Int, pageSize: Int): List<Pattern>
  fun getMyStashes(): List<Stash>
  fun getStashes(username: String): List<Stash>
  fun getMyLibrary(page: Int, pageSize: Int): Call<LibraryResponse>
}