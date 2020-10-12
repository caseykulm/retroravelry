package com.caseykulm.retroravelry

import com.squareup.moshi.Json
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Converter factory for moshi Json annotations on enums so that the same annotations can be used for the Retrofit Service interface parameters.
 *
 */
class MoshiEnumConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<Any, String>? {
        var converter: Converter<Any, String>? = null
        if (type is Class<*> && type.isEnum) {
            converter = Converter { value: Any -> getJsonName(type, value as Enum<*>) }
        }
        return converter
    }
}

private fun getJsonName(type: Type, e: Enum<*>): String? {
    var value: String? = null
    try {
        value = e::class.java.fields
            .first { it.type == type && it.name == e.name }
            .getAnnotation(Json::class.java)
            .name
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
    }
    return value
}
