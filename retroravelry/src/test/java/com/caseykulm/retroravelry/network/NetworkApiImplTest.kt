package com.caseykulm.retroravelry.network

import com.caseykulm.retroravelry.network.error.ErrorDataFactory
import com.caseykulm.retroravelry.network.error.ErrorType
import com.caseykulm.retroravelry.network.error.HttpCode
import com.caseykulm.retroravelry.network.error.HttpErrorData
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NetworkApiImplTest {
  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
  }

  @Test
  fun given500ErrorFromServer_whenFetchData_thenShouldHaveServerErrorInfo() {
    // Given
    val errorDataFactory = ErrorDataFactory()
    val networkApi = NetworkApiImpl(errorDataFactory)

    // When
    val resultSingle = networkApi.fetchData<String>()

    // Then
    resultSingle.subscribe { result ->
      assertFalse(result.isSuccess)
      val errType = result.errorData.getErrorType()
      val httpErrorData: HttpErrorData = result.errorData as HttpErrorData
      val httpCode = httpErrorData.httpCode
      val devMessage = httpErrorData.getDeveloperMessage()
      assertEquals(ErrorType.HTTP, errType)
      assertEquals(HttpCode.SERVER_ERROR, httpCode)
      assertEquals("5xx - Server Error", devMessage)
    }
  }

  @Test
  fun given401ErrorFromServer_whenFetchData_thenShouldHaveServerErrorInfo() {
    // Given
    val errorDataFactory = ErrorDataFactory()
    val networkApi = NetworkApiImpl(errorDataFactory)

    // When
    val resultSingle = networkApi.fetchData<String>()

    // Then
    resultSingle.subscribe { result ->
      assertFalse(result.isSuccess)
      val errType = result.errorData.getErrorType()
      val httpErrorData: HttpErrorData = result.errorData as HttpErrorData
      val httpCode = httpErrorData.httpCode
      val devMessage = httpErrorData.getDeveloperMessage()
      assertEquals(ErrorType.HTTP, errType)
      assertEquals(HttpCode.UNAUTHORIZED, httpCode)
      assertEquals("401 - Unauthorized", devMessage)
    }
  }
}