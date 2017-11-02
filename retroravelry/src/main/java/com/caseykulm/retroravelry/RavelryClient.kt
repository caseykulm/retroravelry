package com.caseykulm.retroravelry

import com.caseykulm.oauthheader.Oauth1Interceptor
import com.caseykulm.retroravelry.models.request.library.Sort
import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.RavelryRetroApi
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.caseykulm.retroravelry.network.responses.patterns.ShowPatternResponse
import com.caseykulm.retroravelry.network.responses.photos.ShowPhotoSizesResponse
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import io.reactivex.Flowable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class RavelryClient(
    val username: String,
    val okHttpClient: OkHttpClient,
    val oauth1Interceptor: Oauth1Interceptor,
    val baseUrl: HttpUrl = HttpUrl.parse("https://api.ravelry.com/")!!): RavelryApi {
  var ravelryRetroApi: RavelryRetroApi

  init {
    val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()
    val oauthClient = okHttpClient.newBuilder()
        .addInterceptor(oauth1Interceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(oauthClient)
        .build()
    ravelryRetroApi = retrofit.create(RavelryRetroApi::class.java)
  }

  override fun searchPatterns(query: String, page: Int, pageSize: Int): Flowable<SearchPatternsResponse> {
    val searchPatternFlowable: Flowable<SearchPatternsResponse> = ravelryRetroApi.searchPatterns(query, page, pageSize, false)
    return searchPatternFlowable
  }

  override fun showPattern(id: Int): Flowable<ShowPatternResponse> {
    val showPatternFlowable: Flowable<ShowPatternResponse> = ravelryRetroApi.showPattern(id)
    return showPatternFlowable
  }

  override fun searchMyLibrary(
      query: String,
      queryType: String?,
      type: Type?,
      sort: Sort?,
      page: Int,
      pageSize: Int
  ): Flowable<LibraryResponse> {
    val libraryFlowable: Flowable<LibraryResponse> = ravelryRetroApi.searchLibrary(username, query, queryType, type, sort, page, pageSize)
    return libraryFlowable
  }

  override fun searchLibrary(
      username: String,
      query: String,
      queryType: String?,
      type: Type?,
      sort: Sort?,
      page: Int,
      pageSize: Int
  ): Flowable<LibraryResponse> {
    val libraryFlowable: Flowable<LibraryResponse> = ravelryRetroApi.searchLibrary(username, query, queryType, type, sort, page, pageSize)
    return libraryFlowable
  }

  override fun showPhotoSizes(photoId: String): Flowable<Result<ShowPhotoSizesResponse>> {
    return ravelryRetroApi.showPhotoDimensions(photoId)
  }
}