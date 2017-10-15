package com.caseykulm.retroravelry

import com.caseykulm.oauthheader.Oauth1Interceptor
import com.caseykulm.retroravelry.network.RavelryRetroApi
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.caseykulm.retroravelry.network.responses.patterns.ShowPatternResponse
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import sun.util.logging.LoggingSupport.setLevel
import okhttp3.logging.HttpLoggingInterceptor



class RavelryClient(
    val username: String,
    val okHttpClient: OkHttpClient,
    val oauth1Interceptor: Oauth1Interceptor): RavelryApi {
  var ravelryRetroApi: RavelryRetroApi

  init {
    //TODO inject moshi and logging interceptor
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()
    val oauthClient = okHttpClient.newBuilder()
        .addInterceptor(logging)
        .addInterceptor(oauth1Interceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.ravelry.com/")
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

  override fun getMyLibrary(
      query: String,
      queryType: String,
      type: String,
      sort: String,
      page: Int,
      pageSize: Int
  ): Call<LibraryResponse> {
    return ravelryRetroApi.searchLibrary(
        username,
        query,
        queryType,
        type,
        sort,
        page,
        pageSize)
  }

  override fun getMyDefaultLibrary(sort: String, page: Int, pageSize: Int): Call<LibraryResponse> {
    return ravelryRetroApi.defaultSearchLibrary(
        username,
        sort,
        page,
        pageSize)
  }
}