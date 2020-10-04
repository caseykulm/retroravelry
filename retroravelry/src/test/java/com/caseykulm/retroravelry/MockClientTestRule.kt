package com.caseykulm.retroravelry

import okhttp3.mockwebserver.MockResponse
import org.junit.rules.ExternalResource

class MockClientTestRule : ExternalResource() {
    lateinit var mockClient: MockClient
    val ravelryClient: RavelryApi by lazy { mockClient.ravelryClient }

    override fun before() {
        mockClient = MockClient()
    }

    override fun after() {
    }

    fun enqueueHttp200(jsonFileName: String) {
        val respJsonStr = javaClass.readResourceFile(jsonFileName)
        mockClient.server.enqueue(
            MockResponse()
                .setResponseCode(200).setBody(respJsonStr)
        )
    }
}
