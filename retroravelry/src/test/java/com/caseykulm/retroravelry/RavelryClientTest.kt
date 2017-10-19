package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Type
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class RavelryClientTest {
  // Used to aid TDD, but make sure to ship not using this
  val liveClient = LiveClient()
  val liveRavelryClient: RavelryApi by lazy { liveClient.ravelryClient }

  @Rule @JvmField val mockClientRule = MockClientTestRule()

  @Test
  fun searchPatternsShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns.json")

    // act
    val searchResponse = mockClientRule.ravelryClient
        .searchPatterns("cardigan", 1, 20)
    val testSubToSearch = searchResponse.test()

    // assert
    testSubToSearch.assertNoErrors()
  }

  @Test
  fun searchPatternsShouldReturnResults() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns.json")

    // act
    val searchResponse = mockClientRule.ravelryClient
        .searchPatterns("cardigan", 1, 20)
    val resp = searchResponse.blockingFirst()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(20, resp.paginator?.page_size)
  }

  @Test
  fun showPatternShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("show_pattern.json")

    // act
    val showResponse = mockClientRule.ravelryClient.showPattern(243083)
    val testSubToShow = showResponse.test()

    // assert
    testSubToShow.assertNoErrors()
  }

  @Test
  fun showPatternShouldReturnResults() {
    // arrange
    mockClientRule.enqueueHttp200("show_pattern.json")

    // act
    val showResponse = mockClientRule.ravelryClient.showPattern(243083)
    val resp = showResponse.blockingFirst()
    println(resp)

    // assert
    assertNotNull(resp)
  }

  @Test
  fun libraryShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = mockClientRule.ravelryClient
        .getMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val testSubToSearchLibrary = libraryResponse.test()

    // assert
    testSubToSearchLibrary.assertNoErrors()
  }

  @Test
  fun libraryShouldReturnPatternResults() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = mockClientRule.ravelryClient
        .getMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val resp = libraryResponse.blockingFirst()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(20, resp.paginator?.page_size)
  }

  @Test
  fun libraryShouldReturnNoBooks() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_books.json")

    // act
    val libraryResponse = mockClientRule.ravelryClient
        .getMyLibrary("duck", null, Type.book, null, 1, 20)
    val resp = libraryResponse.blockingFirst()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(0, resp.paginator?.page_size)
  }
}