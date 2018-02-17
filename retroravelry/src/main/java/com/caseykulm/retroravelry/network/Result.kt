package com.caseykulm.retroravelry.network

import com.caseykulm.retroravelry.network.error.ErrorData

data class Result<ResponseType>(
    val errorData: ErrorData,
    val isSuccess: Boolean
)