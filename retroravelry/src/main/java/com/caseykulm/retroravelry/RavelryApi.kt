package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Sort
import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.caseykulm.retroravelry.network.responses.patterns.ShowPatternResponse
import com.caseykulm.retroravelry.network.responses.photos.ShowPhotoSizesResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.adapter.rxjava2.Result

interface RavelryApi {
  fun searchPatternsRx(query: String, page: Int, pageSize: Int): Single<Result<SearchPatternsResponse>>

  fun searchPatterns(query: String, page: Int, pageSize: Int): Call<SearchPatternsResponse>

  fun showPatternRx(id: Int): Single<Result<ShowPatternResponse>>

  fun showPattern(id: Int): Call<ShowPatternResponse>

  fun searchMyLibraryRx(
      query: String,
      queryType: String?,
      type: Type?,
      sort: Sort?,
      page: Int,
      pageSize: Int
  ): Single<Result<LibraryResponse>>

  fun searchMyLibrary(
      query: String,
      queryType: String?,
      type: Type?,
      sort: Sort?,
      page: Int,
      pageSize: Int
  ): Call<LibraryResponse>

  fun searchLibraryRx(
      username: String,
      query: String,
      queryType: String?,
      type: Type?,
      sort: Sort?,
      page: Int,
      pageSize: Int
  ): Single<Result<LibraryResponse>>

  fun searchLibrary(
      username: String,
      query: String,
      queryType: String?,
      type: Type?,
      sort: Sort?,
      page: Int,
      pageSize: Int
  ): Call<LibraryResponse>

  fun showPhotoSizesRx(photoId: String): Single<Result<ShowPhotoSizesResponse>>

  fun showPhotoSizes(photoId: String): Call<ShowPhotoSizesResponse>
}