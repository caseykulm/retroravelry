package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.auth.AuthorizationInterceptor
import com.caseykulm.retroravelry.auth.OAuth2Authenticator
import com.caseykulm.retroravelry.models.request.library.Sort
import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.RavelryRetroApi
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.caseykulm.retroravelry.network.responses.patterns.ShowPatternResponse
import com.caseykulm.retroravelry.network.responses.photos.ShowPhotoSizesResponse
import com.caseykulm.retroravelry.network.responses.user.CurrentUserResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date

private const val API_URL = "https://api.ravelry.com/"

class RavelryClient(
    oAuth2Authenticator: OAuth2Authenticator,
    okHttpClient: OkHttpClient, // TODO: Make this optional, and provide sensible defaults
    baseUrl: HttpUrl = HttpUrl.parse(API_URL)!! // TODO: Only reveal this as an option for tests
) : RavelryApi {
    private val moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .add(KotlinJsonAdapterFactory())
        .build()
    private val oauthClient = okHttpClient.newBuilder()
        .addInterceptor(AuthorizationInterceptor(oAuth2Authenticator))
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addConverterFactory(MoshiEnumConverterFactory())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(oauthClient)
        .build()
    private val ravelryRetroApi: RavelryRetroApi = retrofit.create(RavelryRetroApi::class.java)

    override suspend fun getCurrentUser(): CurrentUserResponse {
        return ravelryRetroApi.getCurrentUser()
    }

    override suspend fun getPatterns(query: String, page: Int, pageSize: Int): SearchPatternsResponse {
        return ravelryRetroApi.getPatterns(query, page, pageSize, true)
    }

    override fun searchPatternsRx(query: String, page: Int, pageSize: Int): Flowable<Result<SearchPatternsResponse>> {
        return ravelryRetroApi.searchPatternsRx(query, page, pageSize, true)
    }

    override suspend fun getPattern(id: Int): ShowPatternResponse = ravelryRetroApi.getPattern(id)

    override fun showPatternRx(id: Int) = ravelryRetroApi.showPatternRx(id)

    override suspend fun getUserLibrary(
        username: String,
        query: String,
        page: Int,
        pageSize: Int,
        queryType: String?,
        type: Type?,
        sort: Sort?,
    ): LibraryResponse {
        return ravelryRetroApi.getUserLibrary(username, query, queryType, type, sort, page, pageSize)
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

    override suspend fun getPhotoSizes(photoId: Int): ShowPhotoSizesResponse = ravelryRetroApi.getPhotoDimensions(photoId)

    override fun showPhotoSizesRx(photoId: String) = ravelryRetroApi.showPhotoDimensionsRx(photoId)
}
