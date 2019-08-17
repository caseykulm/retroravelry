package com.caseykulm.retroravelry

import com.caseykulm.oauthheader.Oauth1Interceptor
import com.caseykulm.retroravelry.models.request.library.Sort
import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.RavelryRetroApi
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private const val API_URL = "https://api.ravelry.com/"

class RavelryClient(
    private val username: String,
    okHttpClient: OkHttpClient,
    oauth1Interceptor: Oauth1Interceptor,
    baseUrl: HttpUrl = HttpUrl.parse(API_URL)!!
): RavelryApi {
  private var ravelryRetroApi: RavelryRetroApi

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

  override fun searchPatternsRx(query: String, page: Int, pageSize: Int): Single<Result<SearchPatternsResponse>> {
    return ravelryRetroApi.searchPatternsRx(query, page, pageSize, true)
  }

  override fun showPatternRx(id: Int) = ravelryRetroApi.showPatternRx(id)

  override fun searchMyLibraryRx(
      query: String,
      queryType: String?,
      type: Type?,
      sort: Sort?,
      page: Int,
      pageSize: Int
  ): Single<Result<LibraryResponse>> {
    return ravelryRetroApi.searchLibraryRx(username, query, queryType, type, sort, page, pageSize)
  }

  override fun searchLibraryRx(
      username: String,
      query: String,
      queryType: String?,
      type: Type?,
      sort: Sort?,
      page: Int,
      pageSize: Int
  ): Single<Result<LibraryResponse>> {
    return ravelryRetroApi.searchLibraryRx(username, query, queryType, type, sort, page, pageSize)
  }

  override fun showPhotoSizesRx(photoId: String) = ravelryRetroApi.showPhotoDimensionsRx(photoId)
}