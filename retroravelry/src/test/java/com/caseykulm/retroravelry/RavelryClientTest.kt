package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.responses.patterns.SearchPatternsResponse
import com.caseykulm.retroravelry.network.responses.patterns.ShowPatternResponse
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import retrofit2.adapter.rxjava2.Result

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


    // assert
    val body = searchResponse.test().values()[0].response()?.body()
    println(body)
    assertEquals(20, body?.patterns?.size)
  }

  private fun failKt(message: String = "Test failed"): Nothing {
    throw IllegalStateException(message)
  }

  @Test
  fun searchPatternsRxWithShowForEach() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns_tacos.json")
    mockClientRule.enqueueHttp200("show_pattern_taco_1.json")
    mockClientRule.enqueueHttp200("show_pattern_taco_2.json")
    mockClientRule.enqueueHttp200("show_pattern_taco_3.json")

    // act
    val basicPatternsResps: Single<Result<SearchPatternsResponse>> = testClient.searchPatternsRx("taco", 1, 3)

    val fullPatternsResps: Flowable<Result<ShowPatternResponse>> = basicPatternsResps.toFlowable()
        .flatMap { Flowable.fromIterable(it.response()?.body()?.patterns ?: emptyList()) }
        .map { testClient.showPatternRx(it.id).blockingGet() }

    val fullPatterns: List<Result<ShowPatternResponse>> = fullPatternsResps.test().values()

    val tacoPattern1 = fullPatterns[0].response()?.body()?.pattern ?: failKt()
    val tacoPattern2 = fullPatterns[1].response()?.body()?.pattern ?: failKt()
    val tacoPattern3 = fullPatterns[2].response()?.body()?.pattern ?: failKt()

    // assert
    assertEquals(3, fullPatterns.size)
    assertEquals(1, tacoPattern1.craft?.id)
    assertEquals("Crochet", tacoPattern1.craft?.name)
    assertEquals(1, tacoPattern1.craft?.id)
    assertEquals("Crochet", tacoPattern2.craft?.name)
    assertEquals(1, tacoPattern1.craft?.id)
    assertEquals("Crochet", tacoPattern3.craft?.name)
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
    val resp = showResponse.blockingGet()
    println(resp)

    // assert
    assertNotNull(resp)
  }

  @Test
  fun myLibraryRxShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient.searchMyLibraryRx("duck", null, Type.pattern, null, 1, 20)
    val testSubToSearchLibrary = libraryResponse.test()

    // assert
    testSubToSearchLibrary.assertNoErrors()
  }

  @Test
  fun myLibraryRxShouldReturnPatternResults() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient
        .searchMyLibraryRx("duck", null, Type.pattern, null, 1, 20)
    val resp = libraryResponse.blockingGet()
    val libraryResp = resp.response()?.body()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(20, libraryResp?.paginator?.page_size)
  }

  @Test
  fun myLibraryRxShouldReturnNoBooks() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_books.json")

    // act
    val libraryResponse = testClient
        .searchMyLibraryRx("duck", null, Type.book, null, 1, 20)
    val resp = libraryResponse.blockingGet()
    val libraryResp = resp.response()?.body()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(0, libraryResp?.paginator?.page_size)
  }

  @Test
  fun libraryRxShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient.searchLibraryRx("ducksaucer", "duck", null, Type.pattern, null, 1, 20)
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
    val resp = libraryResponse.blockingGet()
    val libraryResp = resp.response()?.body()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(20, libraryResp?.paginator?.page_size)
    assertEquals(5, libraryResp?.volumes?.size)
    val firstVolume = libraryResp?.volumes?.get(0)
    assertEquals(213045775, firstVolume?.id)
    assertEquals("Duck the Sailor", firstVolume?.title)
  }
}