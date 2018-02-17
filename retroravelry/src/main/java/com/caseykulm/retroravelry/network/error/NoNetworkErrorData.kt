package com.caseykulm.retroravelry.network.error

data class NoNetworkErrorData(val devMessage: String): ErrorData {
  override fun getDeveloperMessage(): String {
    return devMessage
  }

  override fun getErrorType(): ErrorType {
    return ErrorType.NO_NETWORK
  }

}