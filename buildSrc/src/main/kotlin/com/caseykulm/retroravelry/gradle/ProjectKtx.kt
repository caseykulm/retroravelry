package com.caseykulm.retroravelry.gradle

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.Properties

fun Project.loadProperties(fileName: String): Properties = Properties().apply {
    load(FileInputStream(rootProject.file(fileName)))
}

fun Project.getSystemVariableOrGradleProperty(name: String, gradleFileName: String): String {
    val prop: String = if (System.getenv()[name] != null) {
        print("Checking for $name in shell system variables... ")
        val sysVar: String = System.getenv()[name] ?: throw IllegalStateException("Failed to parse system variable $name")
        println("Found!")
        sysVar
    } else {
        print("Checking for $name in $gradleFileName... ")
        val gradleVar = loadProperties(gradleFileName).getProperty(name)
        println("Found!")
        gradleVar
    }

    return if (prop.contains("\"")) {
        prop
    } else {
        "\"$prop\""
    }
}