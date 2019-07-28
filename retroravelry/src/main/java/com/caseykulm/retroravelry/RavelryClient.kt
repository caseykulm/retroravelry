package com.caseykulm.retroravelry

import com.caseykulm.oauthheader.Oauth1Interceptor
import com.caseykulm.retroravelry.models.request.library.Sort
import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.RavelryRetroApi
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

private const val API_URL = "https://api.ravelry.com/"

class RavelryClient(
    private val username: String,
    private val okHttpClient: OkHttpClient,
    private val oauth1Interceptor: Oauth1Interceptor,
    private val baseUrl: HttpUrl = HttpUrl.parse(API_URL)!!
): RavelryApi {
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

  override fun searchPatternsRx(query: String, page: Int, pageSize: Int) =
      ravelryRetroApi.searchPatternsRx(query, page, pageSize, true)

  override fun searchPatterns(query: String, page: Int, pageSize: Int) =
      ravelryRetroApi.searchPatterns(query, page, pageSize, true)

  override fun showPatternRx(id: Int) = ravelryRetroApi.showPatternRx(id)

  override fun showPattern(id: Int) = ravelryRetroApi.showPattern(id)

  override fun searchMyLibraryRx(
      query: String, queryType: String?, type: Type?, sort: Sort?, page: Int, pageSize: Int) =
      ravelryRetroApi.searchLibraryRx(username, query, queryType, type, sort, page, pageSize)

  override fun searchMyLibrary(
      query: String, queryType: String?, type: Type?, sort: Sort?, page: Int, pageSize: Int) =
      ravelryRetroApi.searchLibrary(username, query, queryType, type, sort, page, pageSize)

  override fun searchLibraryRx(
      username: String, query: String, queryType: String?, type: Type?, sort: Sort?, page: Int, pageSize: Int) =
      ravelryRetroApi.searchLibraryRx(username, query, queryType, type, sort, page, pageSize)

  override fun searchLibrary(
      username: String, query: String, queryType: String?, type: Type?, sort: Sort?, page: Int, pageSize: Int) =
      ravelryRetroApi.searchLibrary(username, query, queryType, type, sort, page, pageSize)

  override fun showPhotoSizesRx(photoId: String) = ravelryRetroApi.showPhotoDimensionsRx(photoId)

  override fun showPhotoSizes(photoId: String) = ravelryRetroApi.showPhotoDimensions(photoId)
}