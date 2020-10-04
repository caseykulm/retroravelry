import com.caseykulm.retroravelry.gradle.constants.Deps

repositories {
    maven("https://plugins.gradle.org/m2/")
    jcenter()
    mavenCentral()
    mavenLocal()
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.10"
    id("com.jfrog.bintray")
    `java-library`
    `maven-publish`
    id("org.jlleitschuh.gradle.ktlint") version "9.4.0"
    id("retroravelry")
}

// Cannot currently move this to buildSrc because of https://github.com/JLLeitschuh/ktlint-gradle/issues/239
ktlint {
    version.set("0.39.0")
    android.set(false)
}

dependencies {
    implementation(Deps.Kotlin.core)
    implementation(Deps.Kotlin.reflect)
    implementation(Deps.Moshi.core)
    implementation(Deps.Moshi.kotlin)
    implementation(Deps.Moshi.adapters)
    implementation(Deps.OkHttp3.core)
    implementation(Deps.OkHttp3.loggingInterceptor)
    implementation(Deps.Retrofit.converterMoshi)
    implementation(Deps.Retrofit.core)
    implementation(Deps.Retrofit.rxJava2Adapter)
    implementation(Deps.rxjava2)

    testImplementation(Deps.junit4)
    testImplementation(Deps.mockito)
    testImplementation(Deps.OkHttp3.mockWebServer)
}
