package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Sort
import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.caseykulm.retroravelry.network.responses.patterns.ShowPatternResponse
import com.caseykulm.retroravelry.network.responses.photos.ShowPhotoSizesResponse
import com.caseykulm.retroravelry.network.responses.user.CurrentUserResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result

interface RavelryApi {
    suspend fun getCurrentUser(): CurrentUserResponse

    suspend fun getPatterns(query: String, page: Int, pageSize: Int): SearchPatternsResponse

    @Deprecated(
        message = "Deprecating the RxJava2 API in favor of Kotlin Coroutines",
        replaceWith = ReplaceWith(expression = "getPatterns(query, page, pageSie)")
    )
    fun searchPatternsRx(query: String, page: Int, pageSize: Int): Flowable<Result<SearchPatternsResponse>>

    suspend fun getPattern(id: Int): ShowPatternResponse

    @Deprecated(
        message = "Deprecating the RxJava2 API in favor of Kotlin Coroutines",
        replaceWith = ReplaceWith(expression = "getPattern(id)")
    )
    fun showPatternRx(id: Int): Single<Result<ShowPatternResponse>>

    suspend fun getUserLibrary(
        username: String,
        query: String,
        page: Int,
        pageSize: Int,
        queryType: String? = null,
        type: Type? = null,
        sort: Sort? = null,
    ): LibraryResponse

    @Deprecated(
        message = "Deprecating the RxJava2 API in favor of Kotlin Coroutines",
        replaceWith = ReplaceWith(expression = "getUserLibrary(username, query, page, pageSize, queryType, type, sort)")
    )
    fun searchLibraryRx(
        username: String,
        query: String,
        queryType: String?,
        type: Type?,
        sort: Sort?,
        page: Int,
        pageSize: Int
    ): Single<Result<LibraryResponse>>

    suspend fun getPhotoSizes(photoId: Int): ShowPhotoSizesResponse

    @Deprecated(
        message = "Deprecating the RxJava2 API in favor of Kotlin Coroutines",
        replaceWith = ReplaceWith(expression = "getPhotoSizes(photoId)")
    )
    fun showPhotoSizesRx(photoId: String): Single<Result<ShowPhotoSizesResponse>>
}
