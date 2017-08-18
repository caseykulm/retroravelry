package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.entities.Pattern
import com.caseykulm.retroravelry.network.RavelryRetroApi
import com.caseykulm.retroravelry.network.ResponseMapper
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RavelryClient: RavelryApi {
    var ravelryRetroApi: RavelryRetroApi
    var responseMapper: ResponseMapper

    init {
        responseMapper = ResponseMapper()
        val moshi = Moshi.Builder().build()
        val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
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
}