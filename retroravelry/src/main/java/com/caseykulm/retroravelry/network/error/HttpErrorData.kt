package com.caseykulm.retroravelry.network.error

data class HttpErrorData(val httpCode: HttpCode) : ErrorData {
  override fun getErrorType(): ErrorType {
    return ErrorType.HTTP
  }

  override fun getDeveloperMessage(): String {
    when (httpCode) {
      HttpCode.SERVER_ERROR -> return "5xx - Server Error"
      else -> {
        return "Unknown Error"
      }
    }
  }
}