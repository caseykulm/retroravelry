package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Type
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Executors

class RavelryClientTest {
  // Used to aid TDD, but make sure to ship not using this
  val liveClient = LiveClient()
  val liveRavelryClient: RavelryApi by lazy { liveClient.ravelryClient }

  @Rule @JvmField val mockClientRule = MockClientTestRule()

  // Toggle between mock client and live client here. Uncomment which one you want
  val testClient: RavelryApi by lazy {
    mockClientRule.ravelryClient
//     liveRavelryClient
  }

  @Test
  fun searchPatternsRxShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns.json")

    // act
    val searchResponse = testClient.searchPatternsRx("cardigan", 1, 20)
    val testSubToSearch = searchResponse.test()

    // assert
    testSubToSearch.assertNoErrors()
  }

  @Test
  fun searchPatternsRxShouldReturnResults() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns.json")

    // act
    val searchResponse = testClient.searchPatternsRx("cardigan", 1, 20)

//    assertFalse(resp.error()?.message, resp.isError)
//    val patternsResp = resp.response()?.body()
//    println(resp)

    // assert
//    assertNotNull(resp)
//    assertEquals(20, patternsResp?.paginator?.page_size)
    val resp = searchResponse.test()
        .assertValueCount(20)
        .assertComplete()
  }

  @Test
  fun searchPatternsShouldReturnResults() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns.json")

    // act
    val searchResponse = testClient.searchPatterns("cardigan", 1, 20)
    val resp = searchResponse.execute()

    // assert
    assertTrue(resp.message(), resp.isSuccessful)
    val patternsResp = resp.body()
    println(resp)
    assertNotNull(resp)
    assertEquals(20, patternsResp?.paginator?.page_size)
  }

  @Test
  fun searchPatternsRxWithShowForEach() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns_tacos.json")
    mockClientRule.enqueueHttp200("show_pattern_taco_1.json")
    mockClientRule.enqueueHttp200("show_pattern_taco_2.json")
    mockClientRule.enqueueHttp200("show_pattern_taco_3.json")

    // act
    val resultPatternResps = testClient.searchPatternsRx("taco", 1, 3)
        .flatMap { it.response()?.body()?.patterns }
        .map { testClient.showPatternRx(it.id).observeOn(subscribeScheduler) }
        .blockingGet()
    val tacoPattern1 = resultPatternResps[0].response()?.body()?.pattern
    val tacoPattern2 = resultPatternResps[1].response()?.body()?.pattern
    val tacoPattern3 = resultPatternResps[2].response()?.body()?.pattern

    // assert
    assertEquals(3, resultPatternResps.size)
    assertEquals(1, tacoPattern1?.craft?.id)
    assertEquals("Crochet", tacoPattern1?.craft?.name)
    assertEquals(1, tacoPattern1?.craft?.id)
    assertEquals("Crochet", tacoPattern2?.craft?.name)
    assertEquals(1, tacoPattern1?.craft?.id)
    assertEquals("Crochet", tacoPattern3?.craft?.name)
  }

  @Test
  fun showPatternRxShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("show_pattern.json")

    // act
    val showResponse = testClient.showPatternRx(243083)
    val testSubToShow = showResponse.test()

    // assert
    testSubToShow.assertNoErrors()
  }

  @Test
  fun showPatternRxShouldReturnResults() {
    // arrange
    mockClientRule.enqueueHttp200("show_pattern.json")

    // act
    val showResponse = testClient.showPatternRx(243083)

    // assert
    val resp = showResponse.test().assertComplete()
    println(resp)
  }

  @Test
  fun showPatternShouldReturnResults() {
    // arrange
    mockClientRule.enqueueHttp200("show_pattern.json")

    // act
    val showResponse = testClient.showPattern(243083)
    val resp = showResponse.execute()
    println(resp)

    // assert
    assertTrue(resp.message(), resp.isSuccessful)
    assertNotNull(resp.body())
  }

  @Test
  fun myLibraryRxShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient
        .searchMyLibraryRx("duck", null, Type.pattern, null, 1, 20)
    val testSubToSearchLibrary = libraryResponse.test()

    // assert
    testSubToSearchLibrary.assertNoErrors()
  }

  @Test
  fun myLibraryRxShouldReturnPatternResults() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient.searchMyLibraryRx(
        "duck", null, Type.pattern, null, 1, 20
    )

    // assert
    val resp = libraryResponse.test()
        .assertValueCount(20)
        .assertComplete()
    println(resp)
  }

  @Test
  fun myLibraryShouldReturnPatternResults() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient
        .searchMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val resp = libraryResponse.execute()
    val libraryResp = resp.body()
    println(resp)

    // assert
    assertTrue(resp.message(), resp.isSuccessful)
    assertNotNull(resp.body())
    assertEquals(20, libraryResp?.paginator?.page_size)
  }

  @Test
  fun myLibraryRxShouldReturnNoBooks() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_books.json")

    // act
    val libraryResponse = testClient.searchMyLibraryRx(
        "duck", null, Type.book, null, 1, 20
    )

    // assert
    val resp = libraryResponse.test()
        .assertComplete()
        .assertValueCount(0)
    println(resp)
  }

  @Test
  fun libraryRxShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient
        .searchLibraryRx("ducksaucer", "duck", null, Type.pattern, null, 1, 20)
    val testSubToSearchLibrary = libraryResponse.test()

    // assert
    testSubToSearchLibrary.assertNoErrors()
  }

  @Test
  fun libraryRxShouldReturnPatternResults() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient
        .searchMyLibraryRx("duck", null, Type.pattern, null, 1, 20)
    val resp = libraryResponse.test()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(20, libraryResp?.paginator?.page_size)
    assertEquals(5, libraryResp?.volumes?.size)
    val firstVolume = libraryResp?.volumes?.get(0)
    assertEquals(213045775, firstVolume?.id)
    assertEquals("Duck the Sailor", firstVolume?.title)
  }

  @Test
  fun showPhotoDimensionsRx() {
    // arrange
    mockClientRule.enqueueHttp200("show_photo_sizes.json")

    // act
    val photoResponse = testClient.showPhotoSizesRx("17022022")
    val resp = photoResponse.test()
    println(resp)

    // assert
    assertNotNull(resp)
    val url = resp.response()?.raw()?.request()?.url()
    assertEquals("/photos/17022022/sizes.json", url?.encodedPath())
    assertTrue("Response should be success", resp.response()?.isSuccessful == true)
  }

  @Test
  fun showPhotoDimensions() {
    // arrange
    mockClientRule.enqueueHttp200("show_photo_sizes.json")

    // act
    val photoResponse = testClient.showPhotoSizes("17022022")
    val resp = photoResponse.execute()
    println(resp)

    // assert
    assertNotNull(resp)
    val url = resp.raw().request().url()
    assertEquals("/photos/17022022/sizes.json", url.encodedPath())
    assertTrue("Response should be success", resp.isSuccessful)
  }
}