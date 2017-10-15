package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Sort
import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.caseykulm.retroravelry.network.responses.patterns.ShowPatternResponse
import io.reactivex.Flowable
import retrofit2.Call

interface RavelryApi {
  fun searchPatterns(query: String, page: Int, pageSize: Int): Flowable<SearchPatternsResponse>
  fun showPattern(id: Int): Flowable<ShowPatternResponse>
  fun getMyLibrary(
      query: String,
      queryType: String?,
      type: Type?,
      sort: Sort?,
      page: Int,
      pageSize: Int): Flowable<LibraryResponse>
}