package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Type
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class RavelryClientTest {
  val ravelryClient = LiveClient().ravelryClient

  @Test
  fun searchPatternsShouldSubscribe() {
    val searchResponse = ravelryClient.searchPatterns("cardigan", 1, 20)
    val testSubToSearch = searchResponse.test()
    testSubToSearch.assertNoErrors()
  }

  @Test
  fun searchPatternsShouldReturnResults() {
    val searchResponse = ravelryClient.searchPatterns("cardigan", 1, 20)
    val resp = searchResponse.blockingFirst()
    println(resp)
    assertNotNull(resp)
    assertEquals(20, resp.paginator?.page_size)
  }

  @Test
  fun showPatternsShouldSubscribe() {
    val showResponse = ravelryClient.showPattern(243083)
    val testSubToShow = showResponse.test()
    testSubToShow.assertNoErrors()
  }

  @Test
  fun showPatternsShouldReturnResults() {
    val showResponse = ravelryClient.showPattern(243083)
    val resp = showResponse.blockingFirst()
    println(resp)
    assertNotNull(resp)
  }

  @Test
  fun libraryShouldSubscribe() {
    val libraryResponse = ravelryClient.getMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val testSubToSearchLibrary = libraryResponse.test()
    testSubToSearchLibrary.assertNoErrors()
  }

  @Test
  fun libraryShouldReturnResults() {
    val libraryResponse = ravelryClient.getMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val resp = libraryResponse.blockingFirst()
    println(resp)
    assertNotNull(resp)
    assertEquals(20, resp.paginator?.page_size)
  }

  @Test
  fun libraryShouldReturnNothing() {
    val libraryResponse = ravelryClient.getMyLibrary("duck", null, Type.book, null, 1, 20)
    val resp = libraryResponse.blockingFirst()
    println(resp)
    assertNotNull(resp)
    assertEquals(0, resp.paginator?.page_size)
  }
}