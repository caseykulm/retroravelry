import com.caseykulm.retroravelry.gradle.constants.Deps
import com.caseykulm.retroravelry.gradle.constants.Versions

repositories {
    maven("https://plugins.gradle.org/m2/")
    jcenter()
    mavenCentral()
    mavenLocal()
}

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("com.jfrog.bintray")
    `java-library`
    `maven-publish`
    id("org.jlleitschuh.gradle.ktlint") version "9.4.0"
    id("retroravelry")
}

// Cannot currently move this to buildSrc because of https://github.com/JLLeitschuh/ktlint-gradle/issues/239
ktlint {
    version.set(Versions.ktlint)
    android.set(false)
}

dependencies {
    implementation(Deps.Kotlin.core)
    implementation(Deps.Kotlin.coroutines)
    implementation(Deps.Moshi.core)
    implementation(Deps.Moshi.kotlin)
    implementation(Deps.Moshi.adapters)
    kapt(Deps.Moshi.codegen)
    implementation(Deps.OkHttp3.core)
    implementation(Deps.OkHttp3.loggingInterceptor)
    implementation(Deps.Retrofit.converterMoshi)
    implementation(Deps.Retrofit.core)
    implementation(Deps.Retrofit.rxJava2Adapter)
    implementation(Deps.rxjava2)
    implementation(kotlin(Deps.Kotlin.jdk8))

    testImplementation(Deps.JUnit5.core)
    testRuntimeOnly(Deps.JUnit5.runtime)
    testImplementation(Deps.mockito)
    testImplementation(Deps.OkHttp3.mockWebServer)
    testImplementation(Deps.Kotlin.test)
    testImplementation(Deps.Kotlin.testCommon)
    testImplementation(Deps.Kotlin.testJunit5)
}
