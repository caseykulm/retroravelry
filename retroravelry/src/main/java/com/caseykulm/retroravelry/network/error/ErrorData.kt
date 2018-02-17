package com.caseykulm.retroravelry.network.error

interface ErrorData {
  fun getDeveloperMessage(): String
  fun getErrorType(): ErrorType
}