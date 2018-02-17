package com.caseykulm.retroravelry.network

import io.reactivex.Single

interface NetworkApi {
  fun <ResponseType> fetchData(): Single<Result<ResponseType>>
}