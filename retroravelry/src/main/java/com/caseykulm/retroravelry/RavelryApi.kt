package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.entities.Stash
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import io.reactivex.Flowable
import retrofit2.Call

interface RavelryApi {
  fun searchPatterns(query: String, page: Int, pageSize: Int): Flowable<SearchPatternsResponse>
  fun getMyStashes(): List<Stash>
  fun getStashes(username: String): List<Stash>
  fun getMyLibrary(
      query: String,
      queryType: String,
      type: String,
      sort: String,
      page: Int,
      pageSize: Int): Call<LibraryResponse>
  fun getMyDefaultLibrary(
      sort: String,
      page: Int, pageSize: Int): Call<LibraryResponse>
}