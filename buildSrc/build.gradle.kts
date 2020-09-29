repositories {
    jcenter()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
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
}