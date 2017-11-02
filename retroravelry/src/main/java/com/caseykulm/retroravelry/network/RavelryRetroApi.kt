package com.caseykulm.retroravelry.network

import com.caseykulm.retroravelry.models.request.library.Sort
import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.caseykulm.retroravelry.network.responses.patterns.ShowPatternResponse
import com.caseykulm.retroravelry.network.responses.photos.ShowPhotoSizesResponse
import io.reactivex.Flowable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val API_URL = "https://api.ravelry.com/"

interface RavelryRetroApi {

  // region Patterns

  @GET("patterns/search.json")
  fun searchPatterns(
      @Query("query") query: String,
      @Query("page") page: Int,
      @Query("page_size") pageSize: Int,
      @Query("personal_attributes") personal_attributes: Boolean): Flowable<SearchPatternsResponse>

  @GET("patterns/{id}.json")
  fun showPattern(@Path("id") id: Int): Flowable<ShowPatternResponse>

  // endregion

  // region Library

  @GET("people/{username}/library/search.json")
  fun searchLibrary(
      @Path("username") username: String,
      @Query("query") query: String,
      @Query("query_type") queryType: String?,
      @Query("type") type: Type?,
      @Query("sort") sort: Sort?,
      @Query("page") page: Int,
      @Query("page_size") pageSize: Int): Flowable<LibraryResponse>
  
  // endregion

  // region Photos
  @GET("photos/{id}/sizes.json")
  fun showPhotoDimensions(@Path("id") photoId: String): Flowable<Result<ShowPhotoSizesResponse>>

  // endregion
}
