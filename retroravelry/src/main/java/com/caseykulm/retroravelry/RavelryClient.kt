package com.caseykulm.retroravelry

import com.caseykulm.oauthheader.Oauth1Interceptor
import com.caseykulm.oauthheader.models.AccessTokenResponse
import com.caseykulm.oauthheader.models.AuthorizationResponse
import com.caseykulm.retroravelry.entities.Pattern
import com.caseykulm.retroravelry.entities.Stash
import com.caseykulm.retroravelry.network.RavelryRetroApi
import com.caseykulm.retroravelry.network.ResponseMapper
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class RavelryClient(
    val username: String,
    val okHttpClient: OkHttpClient,
    val oauth1Interceptor: Oauth1Interceptor): RavelryApi {
  var ravelryRetroApi: RavelryRetroApi
  var responseMapper: ResponseMapper

  init {
    responseMapper = ResponseMapper()
    val moshi = Moshi.Builder().build()
    val oauthClient = okHttpClient.newBuilder()
        .addInterceptor(oauth1Interceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.ravelry.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(oauthClient)
        .build()
    ravelryRetroApi = retrofit.create(RavelryRetroApi::class.java)
  }

  override fun searchPatterns(query: String, page: Int, pageSize: Int): List<Pattern> {
    val patternList = ArrayList<Pattern>()
    val retroResp = ravelryRetroApi.searchPatterns(
        query, page, pageSize, false)
    val resp = retroResp.execute()
    if (resp.isSuccessful) {
      val searchPatternResp = resp.body()
      searchPatternResp?.patterns?.forEach {
        patternList.add(responseMapper.toPatternEntity(it))
      }
      return patternList
    }
    throw IllegalStateException("failed to search: " + resp.code())
  }

  override fun getMyStashes(): List<Stash> {
    return getStashes(username)
  }

  override fun getStashes(username: String): List<Stash> {
    val retroResp = ravelryRetroApi.getStashes(username)
    val resp = retroResp.execute()
    if (resp.isSuccessful) {
      val stashesResp = resp.body()
      return stashesResp.stashes
    }
    throw IllegalStateException("failed to search: " + resp.code())
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