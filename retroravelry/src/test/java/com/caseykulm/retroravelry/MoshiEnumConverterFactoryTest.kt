package com.caseykulm.retroravelry

import com.squareup.moshi.Json
import retrofit2.Retrofit
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalStdlibApi
internal class MoshiEnumConverterFactoryTest {
    private val subj = MoshiEnumConverterFactory()
    private enum class StubEnum {
        @Json(name = "customFoo") FOO,
        BAR
    }

    @Test
    fun `Given StubEnum FOO type, When convert, Then should be customFoo`() {
        val stubEnum = StubEnum.FOO
        val stubEnumType = StubEnum::class.java
        val stubEnumAnnotations = stubEnum::class.java.annotations
        val stubEnumConverter = subj.stringConverter(
            type = stubEnumType,
            annotations = stubEnumAnnotations,
            retrofit = Retrofit.Builder().baseUrl("https://website.com").build()
        )

        val actualValue = stubEnumConverter?.convert(stubEnum)

        assertEquals(
            expected = "customFoo",
            actual = actualValue
        )
    }

    @Test
    fun `Given StubEnum BAR type, When convert, Then should be BAR`() {
        val stubEnum = StubEnum.BAR
        val stubEnumType = StubEnum::class.java
        val stubEnumAnnotations = stubEnum::class.java.annotations
        val stubEnumConverter = subj.stringConverter(
            type = stubEnumType,
            annotations = stubEnumAnnotations,
            retrofit = Retrofit.Builder().baseUrl("https://website.com").build()
        )

        val actualValue = stubEnumConverter?.convert(stubEnum)

        assertEquals(
            expected = "BAR",
            actual = actualValue
        )
    }
}
