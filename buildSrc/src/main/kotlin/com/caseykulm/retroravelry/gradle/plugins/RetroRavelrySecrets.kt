package com.caseykulm.retroravelry.gradle.plugins

import com.caseykulm.retroravelry.gradle.getSystemVariableOrGradleProperty
import org.gradle.api.Project

data class RetroRavelrySecrets(
    val bintraySecrets: BintraySecrets,
    val sonatypeSecrets: SonatypeSecrets
)

data class BintraySecrets(
    val user: String,
    val key: String,
    val gpgPassphrase: String
)

data class SonatypeSecrets(
    val user: String,
    val password: String
)

fun Project.loadRetroRavelrySecrets(): RetroRavelrySecrets {
    val gradlePropertiesFileName = "secrets.properties"
    return RetroRavelrySecrets(
        bintraySecrets = BintraySecrets(
            user = getSystemVariableOrGradleProperty("BINTRAY_USER", gradlePropertiesFileName),
            key = getSystemVariableOrGradleProperty("BINTRAY_API_KEY", gradlePropertiesFileName),
            gpgPassphrase = getSystemVariableOrGradleProperty("BINTRAY_GPG_PASSWORD", gradlePropertiesFileName)
        ),
        sonatypeSecrets = SonatypeSecrets(
            user = getSystemVariableOrGradleProperty("SONATYPE_USER", gradlePropertiesFileName),
            password = getSystemVariableOrGradleProperty("SONATYPE_PASSWORD", gradlePropertiesFileName)
        )
    )
}