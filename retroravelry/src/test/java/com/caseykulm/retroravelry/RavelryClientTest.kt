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
  fun searchPatternsShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns.json")

    // act
    val searchResponse = testClient.searchPatterns("cardigan", 1, 20)
    val testSubToSearch = searchResponse.test()

    // assert
    testSubToSearch.assertNoErrors()
  }

  @Test
  fun searchPatternsShouldReturnResults() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns.json")

    // act
    val searchResponse = testClient.searchPatterns("cardigan", 1, 20)
    val resp = searchResponse.blockingFirst()
    assertFalse(resp.error()?.message, resp.isError)
    val patternsResp = resp.response().body()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(20, patternsResp.paginator?.page_size)
  }

  @Test
  fun searchPatternsWithShowForEach() {
    // arrange
    mockClientRule.enqueueHttp200("search_patterns_tacos.json")
    mockClientRule.enqueueHttp200("show_pattern_taco_1.json")
    mockClientRule.enqueueHttp200("show_pattern_taco_2.json")
    mockClientRule.enqueueHttp200("show_pattern_taco_3.json")

    val subscribeExecutor = Executors.newCachedThreadPool()
    val subscribeScheduler = Schedulers.from(subscribeExecutor)

    // act
    val resultPatternResps = testClient.searchPatterns("taco", 1, 3)
        .doOnNext { if (it.isError) { it.error()?.message } }
        .flatMap { Flowable.fromIterable(it.response().body().patterns) }
        .flatMap { testClient.showPattern(it.id).observeOn(subscribeScheduler) }
        .doOnNext { println("Running on thread id: ${Thread.currentThread().id}")}
        .toList()
        .blockingGet()
    val tacoPattern1 = resultPatternResps[0].response().body().pattern
    val tacoPattern2 = resultPatternResps[1].response().body().pattern
    val tacoPattern3 = resultPatternResps[2].response().body().pattern

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
  fun showPatternShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("show_pattern.json")

    // act
    val showResponse = testClient.showPattern(243083)
    val testSubToShow = showResponse.test()

    // assert
    testSubToShow.assertNoErrors()
  }

  @Test
  fun showPatternShouldReturnResults() {
    // arrange
    mockClientRule.enqueueHttp200("show_pattern.json")

    // act
    val showResponse = testClient.showPattern(243083)
    val resp = showResponse.blockingFirst()
    println(resp)

    // assert
    assertNotNull(resp)
  }

  @Test
  fun myLibraryShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient
        .searchMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val testSubToSearchLibrary = libraryResponse.test()

    // assert
    testSubToSearchLibrary.assertNoErrors()
  }

  @Test
  fun myLibraryShouldReturnPatternResults() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient
        .searchMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val resp = libraryResponse.blockingFirst()
    val libraryResp = resp.response().body()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(20, libraryResp.paginator?.page_size)
  }

  @Test
  fun myLibraryShouldReturnNoBooks() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_books.json")

    // act
    val libraryResponse = testClient
        .searchMyLibrary("duck", null, Type.book, null, 1, 20)
    val resp = libraryResponse.blockingFirst()
    val libraryResp = resp.response().body()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(0, libraryResp.paginator?.page_size)
  }

  @Test
  fun libraryShouldSubscribe() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient
        .searchLibrary("ducksaucer", "duck", null, Type.pattern, null, 1, 20)
    val testSubToSearchLibrary = libraryResponse.test()

    // assert
    testSubToSearchLibrary.assertNoErrors()
  }

  @Test
  fun libraryShouldReturnPatternResults() {
    // arrange
    mockClientRule.enqueueHttp200("my_library_patterns.json")

    // act
    val libraryResponse = testClient
        .searchMyLibrary("duck", null, Type.pattern, null, 1, 20)
    val resp = libraryResponse.blockingFirst()
    val libraryResp = resp.response().body()
    println(resp)

    // assert
    assertNotNull(resp)
    assertEquals(20, libraryResp.paginator?.page_size)
    assertEquals(5, libraryResp.volumes?.size)
    val firstVolume = libraryResp.volumes!!.get(0)
    assertEquals(213045775, firstVolume.id)
    assertEquals("Duck the Sailor", firstVolume.title)
  }

  @Test
  fun showPhotoDimensions() {
    // arrange
    mockClientRule.enqueueHttp200("show_photo_sizes.json")

    // act
    val photoResponse = testClient.showPhotoSizes("17022022")
    val resp = photoResponse.blockingFirst()
    println(resp)

    // assert
    assertNotNull(resp)
    val url = resp.response().raw().request().url()
    assertEquals("/photos/17022022/sizes.json", url.encodedPath())
    assertTrue("Response should be success", resp.response().isSuccessful)
  }
}