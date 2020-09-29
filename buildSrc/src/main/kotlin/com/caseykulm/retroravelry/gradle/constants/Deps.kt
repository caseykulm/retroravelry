package com.caseykulm.retroravelry.gradle.constants

object Deps {
    const val junit4 = "junit:junit:${Versions.junit4}"
    object Kotlin {
        const val core = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    object Moshi {
        const val adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
        const val core = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    }
    object OkHttp3 {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp3}"
    }
    object Retrofit {
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val rxJava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    }
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava2}"
}