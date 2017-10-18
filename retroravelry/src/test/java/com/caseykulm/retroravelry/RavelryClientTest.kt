package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Type
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class RavelryClientTest {
  // Used to aide TDD, but make sure to ship not using this
  val liveClient = LiveClient()
  val liveRavelryClient: RavelryApi by lazy { liveClient.ravelryClient }

  // Used to finalize tests against saved JSON responses from the API
  val mockClient = MockClient()
  val mockRavelryClient: RavelryApi by lazy { mockClient.ravelryClient }
  val mockServer: MockWebServer by lazy { mockClient.server }

  @Test
  fun searchPatternsShouldSubscribe() {
    val searchResponse = liveRavelryClient.searchPatterns("cardigan", 1, 20)
    val testSubToSearch = searchResponse.test()
    testSubToSearch.assertNoErrors()
  }

  @Test
  fun searchPatternsShouldReturnResults() {
    val searchResponse = liveRavelryClient.searchPatterns("cardigan", 1, 20)
    val resp = searchResponse.blockingFirst()
    println(resp)
    assertNotNull(resp)
    assertEquals(20, resp.paginator?.page_size)
  }

  @Test
  fun showPatternsShouldSubscribe() {
    val showResponse = liveRavelryClient.showPattern(243083)
    val testSubToShow = showResponse.test()
    testSubToShow.assertNoErrors()
  }

  @Test
  fun showPatternsShouldReturnResults() {
    val showResponse = liveRavelryClient.showPattern(243083)
    val resp = showResponse.blockingFirst()
    println(resp)
    assertNotNull(resp)
  }

  @Test
  fun libraryShouldSubscribe() {
    val libraryResponse = liveRavelryClient.getMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val testSubToSearchLibrary = libraryResponse.test()
    testSubToSearchLibrary.assertNoErrors()
  }

  @Test
  fun libraryShouldReturnResults() {
    val libraryResponse = liveRavelryClient.getMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val resp = libraryResponse.blockingFirst()
    println(resp)
    assertNotNull(resp)
    assertEquals(20, resp.paginator?.page_size)
  }

  @Test
  fun libraryShouldReturnNothing() {
    val libraryResponse = liveRavelryClient.getMyLibrary("duck", null, Type.book, null, 1, 20)
    val resp = libraryResponse.blockingFirst()
    println(resp)
    assertNotNull(resp)
    assertEquals(0, resp.paginator?.page_size)
  }
}