package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.auth.AuthProvider
import com.caseykulm.retroravelry.auth.AuthorizationInterceptor
import com.caseykulm.retroravelry.models.request.library.Sort
import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.RavelryRetroApi
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private const val API_URL = "https://api.ravelry.com/"

class RavelryClient(
    authProvider: AuthProvider,
    okHttpClient: OkHttpClient, // TODO: Make this optional, and provide sensible defaults
    baseUrl: HttpUrl = HttpUrl.parse(API_URL)!! // TODO: Only reveal this as an option for tests
): RavelryApi {
  private var ravelryRetroApi: RavelryRetroApi

  init {
    val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()
    val oauthClient = okHttpClient.newBuilder()
        .addInterceptor(AuthorizationInterceptor(authProvider))
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(oauthClient)
        .build()
    ravelryRetroApi = retrofit.create(RavelryRetroApi::class.java)
  }

  override fun searchPatternsRx(query: String, page: Int, pageSize: Int): Flowable<Result<SearchPatternsResponse>> {
    return ravelryRetroApi.searchPatternsRx(query, page, pageSize, true)
  }

  override fun showPatternRx(id: Int) = ravelryRetroApi.showPatternRx(id)

  override fun searchMyLibraryRx(
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