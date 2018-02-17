package com.caseykulm.retroravelry.network.error

class ErrorDataFactory {
  fun createErrorData(errorType: ErrorType, httpCode: HttpCode): ErrorData {
    when (errorType) {
      ErrorType.HTTP -> return HttpErrorData(httpCode)
      ErrorType.NO_NETWORK -> return NoNetworkErrorData("no network")
    }
  }
}