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

    fun searchPatternsRx(query: String, page: Int, pageSize: Int): Flowable<Result<SearchPatternsResponse>>

    fun showPatternRx(id: Int): Single<Result<ShowPatternResponse>>

    fun searchMyLibraryRx(
        username: String,
        query: String,
        queryType: String?,
        type: Type?,
        sort: Sort?,
        page: Int,
        pageSize: Int
    ): Single<Result<LibraryResponse>>

    fun searchLibraryRx(
        username: String,
        query: String,
        queryType: String?,
        type: Type?,
        sort: Sort?,
        page: Int,
        pageSize: Int
    ): Single<Result<LibraryResponse>>

    fun showPhotoSizesRx(photoId: String): Single<Result<ShowPhotoSizesResponse>>
}
