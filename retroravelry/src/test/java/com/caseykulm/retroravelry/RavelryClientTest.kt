package com.caseykulm.retroravelry

import com.caseykulm.retroravelry.models.request.library.Type
import com.caseykulm.retroravelry.models.response.pattern.FullPattern
import com.caseykulm.retroravelry.network.responses.library.LibraryResponse
import io.reactivex.Flowable
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.extension.RegisterExtension
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.fail

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

    @Test
    fun `Given success, When getPatterns, Then should have valid data`() = runBlocking {
        getPatternsSetup()

        val fullPatterns: List<FullPattern> = testClient.getPatterns(query = "taco", page = 1, pageSize = 3)
            .patterns!!
            .map { testClient.getPattern(it.id).pattern!! }

        getPatternsAssertions(fullPatterns)
    }

    @Test
    fun searchPatternsRxWithShowForEach() {
        // arrange
        getPatternsSetup()

        // act
        val fullPatternsResps: Flowable<FullPattern> = testClient.searchPatternsRx("taco", 1, 3)
            .flatMap { Flowable.fromIterable(it.response()?.body()?.patterns ?: emptyList()) }
            .map { testClient.showPatternRx(it.id).blockingGet().response()?.body()?.pattern ?: fail() }

        val fullPatterns: List<FullPattern> = fullPatternsResps.test().values()

        getPatternsAssertions(fullPatterns)
    }

    private fun getPatternsSetup() {
        mockClientTestExtension.enqueueHttp200("search_patterns_tacos.json")
        mockClientTestExtension.enqueueHttp200("show_pattern_taco_1.json")
        mockClientTestExtension.enqueueHttp200("show_pattern_taco_2.json")
        mockClientTestExtension.enqueueHttp200("show_pattern_taco_3.json")
    }

    private fun getPatternsAssertions(fullPatterns: List<FullPattern>) {
        val tacoPattern1 = fullPatterns[0]
        val tacoPattern2 = fullPatterns[1]
        val tacoPattern3 = fullPatterns[2]

        assertEquals(expected = 3, actual = fullPatterns.size)
        assertEquals(expected = 1, actual = tacoPattern1.craft?.id)
        assertEquals(expected = "Crochet", actual = tacoPattern1.craft?.name)
        assertEquals(expected = 1, actual = tacoPattern1.craft?.id)
        assertEquals(expected = "Knitting", actual = tacoPattern2.craft?.name)
        assertEquals(expected = 1, actual = tacoPattern1.craft?.id)
        assertEquals(expected = "Crochet", actual = tacoPattern3.craft?.name)
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
    fun `Given success, When searchLibraryRx, Then should have valid data`() {
        // arrange
        getUserLibrarySetup()

        // act
        val libraryResponse = testClient.searchLibraryRx("ducksaucer", "duck", null, Type.Pattern, null, 1, 20)

        // assert
        getUserLibraryAssertions(libraryResponse.blockingGet().response()!!.body()!!)
    }

    @Test
    fun `Given success, When getUserLibrary, Then should have valid data`() = runBlocking {
        getUserLibrarySetup()

        val libraryResponse: LibraryResponse = testClient.getUserLibrary(
            username = "ducksaucer",
            query = "duck",
            queryType = null,
            page = 1,
            pageSize = 20,
            type = Type.Pattern,
            sort = null
        )

        getUserLibraryAssertions(libraryResponse)
    }

    private fun getUserLibrarySetup() {
        mockClientTestExtension.enqueueHttp200("my_library_patterns.json")
    }

    private fun getUserLibraryAssertions(libraryResponse: LibraryResponse) {
        assertNotNull(libraryResponse)
        assertEquals(expected = 5, libraryResponse.paginator?.results)
        assertEquals(expected = 5, libraryResponse.volumes?.size)
    }

    @Test
    fun `Given success, When getCurrentUser, Then should have valid data`() = runBlocking {
        mockClientTestExtension.enqueueHttp200("current_user_resp.json")

        val currentUser = testClient.getCurrentUser().smallUser
        println(currentUser)

        assertEquals(expected = stubUserName, actual = currentUser.username)
    }
}
