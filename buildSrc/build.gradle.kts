import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    jcenter()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    kotlin("jvm") version "1.4.10"
    kotlin("kapt") version "1.4.10"
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

gradlePlugin {
    plugins {
        create("retroRaveleryPlugin") {
            id = "retroravelry"
            implementationClass = "com.caseykulm.retroravelry.gradle.plugins.RetroRavelryPlugin"
        }
    }
}

dependencies {
    implementation(gradleApi())
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
}
