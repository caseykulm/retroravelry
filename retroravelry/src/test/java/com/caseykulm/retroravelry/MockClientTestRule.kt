package com.caseykulm.retroravelry

import okhttp3.mockwebserver.MockResponse
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
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

class MockClientTestExtension : BeforeAllCallback {
    lateinit var mockClient: MockClient
    val ravelryClient: RavelryApi by lazy { mockClient.ravelryClient }

    override fun beforeAll(context: ExtensionContext) {
        mockClient = MockClient()
    }

    fun enqueueHttp200(jsonFileName: String) {
        val respJsonStr = javaClass.readResourceFile(jsonFileName)
        mockClient.server.enqueue(
            MockResponse()
                .setResponseCode(200).setBody(respJsonStr)
        )
    }
}
