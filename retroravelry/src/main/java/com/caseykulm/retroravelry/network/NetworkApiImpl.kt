package com.caseykulm.retroravelry.network

import com.caseykulm.retroravelry.network.error.ErrorDataFactory
import com.caseykulm.retroravelry.network.error.ErrorType
import com.caseykulm.retroravelry.network.error.HttpCode
import io.reactivex.Single

class NetworkApiImpl(
    private val errorDataFactory: ErrorDataFactory) : NetworkApi {
  override fun <ResponseType> fetchData(): Single<Result<ResponseType>> {
    val errorType = ErrorType.HTTP
    val httpCode = HttpCode.SERVER_ERROR
    val isError = false
    val errorData = errorDataFactory.createErrorData(errorType, httpCode)
    val result = Result<ResponseType>(errorData, isError)
    return Single.just(result)
  }

}