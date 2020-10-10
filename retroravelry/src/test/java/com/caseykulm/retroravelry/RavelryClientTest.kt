package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.network.responses.patterns.ShowPatternResponse
import io.reactivex.Flowable
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.extension.RegisterExtension
import retrofit2.adapter.rxjava2.Result
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class RavelryClientTest {
    // Used to aid TDD, but make sure to ship not using this
    val liveClient = LiveClient()
    val liveRavelryClient: RavelryApi by lazy { liveClient.ravelryClient }

    companion object {
        @JvmField @RegisterExtension val mockClientTestExtension = MockClientTestExtension()
    }

    // Toggle between mock client and live client here. Uncomment which one you want
    val testClient: RavelryApi by lazy {
        mockClientTestExtension.ravelryClient
//     liveRavelryClient // Note: You also need to update the accessToken in oauth_secrets.json for this to work
    }

    val stubUserName = "rumpletestuser" // substitute your Ravelry user id here

    @Test
    fun searchPatternsRxShouldSubscribe() {
        // arrange
        mockClientTestExtension.enqueueHttp200("search_patterns.json")

        // act
        val searchResponse = testClient.searchPatternsRx("cardigan", 1, 20)
        val testSubToSearch = searchResponse.test()

        // assert
        testSubToSearch.assertNoErrors()
    }

    @Test
    fun `Given query with spaces, When search patterns rx, Then return results`() {
        // arrange
        mockClientTestExtension.enqueueHttp200("search_patterns.json")

        val stubQuery = "baby hat"

        val searchResponse = testClient.searchPatternsRx(stubQuery, 1, 20)
        val result = searchResponse.blockingFirst()
        val response = result.response()!!
        println(response)

        assertTrue(response.isSuccessful, response.errorBody()?.charStream()?.readText())
    }

    @Test
    fun `Given query with no spaces, When search patterns rx, Then return results`() {
        // arrange
        mockClientTestExtension.enqueueHttp200("search_patterns.json")

        // act
        val searchResponse = testClient.searchPatternsRx("cardigan", 1, 20)
        val result = searchResponse.blockingFirst()
        val response = result.response()!!
        assertTrue(response.isSuccessful, response.errorBody().toString())
        val patternsResp = result.response()?.body()
        println(result)

        // assert
        assertNotNull(result)
        assertEquals(20, patternsResp?.paginator?.pageSize)
    }

    private fun failKt(message: String = "Test failed"): Nothing {
        throw IllegalStateException(message)
    }

    @Test
    fun searchPatternsRxWithShowForEach() {
        // arrange
        mockClientTestExtension.enqueueHttp200("search_patterns_tacos.json")
        mockClientTestExtension.enqueueHttp200("show_pattern_taco_1.json")
        mockClientTestExtension.enqueueHttp200("show_pattern_taco_2.json")
        mockClientTestExtension.enqueueHttp200("show_pattern_taco_3.json")

        // act
        val fullPatternsResps: Flowable<Result<ShowPatternResponse>> = testClient.searchPatternsRx("taco", 1, 3)
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
        assertEquals("Knitting", tacoPattern2.craft?.name)
        assertEquals(1, tacoPattern1.craft?.id)
        assertEquals("Crochet", tacoPattern3.craft?.name)
    }

    @Test
    fun showPatternRxShouldSubscribe() {
        // arrange
        mockClientTestExtension.enqueueHttp200("show_pattern.json")

        // act
        val showResponse = testClient.showPatternRx(243083)
        val testSubToShow = showResponse.test()

        // assert
        testSubToShow.assertNoErrors()
    }

    @Test
    fun showPatternRxShouldReturnResults() {
        // arrange
        mockClientTestExtension.enqueueHttp200("show_pattern.json")

        // act
        val showResponse = testClient.showPatternRx(243083)
        val resp = showResponse.blockingGet()
        println(resp)

        // assert
        assertNotNull(resp)
    }

    @Test
    fun libraryRxShouldSubscribe() {
        // arrange
        mockClientTestExtension.enqueueHttp200("my_library_patterns.json")

        // act
        val libraryResponse = testClient.searchLibraryRx("ducksaucer", "duck", null, Type.Pattern, null, 1, 20)
        val testSubToSearchLibrary = libraryResponse.test()

        // assert
        testSubToSearchLibrary.assertNoErrors()
    }

    @Test
    fun `Given success, When getCurrentUser, Then should valid data`() = runBlocking {
        mockClientTestExtension.enqueueHttp200("current_user_resp.json")

        val currentUser = testClient.getCurrentUser().smallUser
        println(currentUser)

        assertEquals(expected = stubUserName, actual = currentUser.username)
    }
}
