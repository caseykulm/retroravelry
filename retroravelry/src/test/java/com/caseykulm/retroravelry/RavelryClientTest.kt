package com.caseykulm.retroravelry

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class RavelryClientTest {
  val ravelryClient = LiveClient().ravelryClient

  @Test
  fun getMyLibraryShouldNotBeNull() {
    val libResp = ravelryClient.getMyDefaultLibrary("added", 0, 20).execute().body()
    println(libResp)
    assertNotNull(libResp)
  }

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
}