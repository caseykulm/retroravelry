package com.caseykulm.retroravelry.gradle.plugins

import com.caseykulm.retroravelry.gradle.constants.MavenInfo
import com.caseykulm.retroravelry.gradle.constants.Versions
import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.named
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import java.io.File
import java.util.Date

class RetroRavelryPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addBumpMinorVersionTask()

        project.afterEvaluate {
            configureJavaLibraryPlugin()
            configureMavenPlugin()
            configureBintrayPlugin(project, loadRetroRavelrySecrets())
            configureJunit5()
            configureKapt()
        }
    }
}

private val semVerRegexString = "(\\d+).(\\d+).(\\d+)-?(.*)"
private val versionsFileRelativePath = "com/caseykulm/retroravelry/gradle/constants/Versions.kt"
private val versionsFile = File("buildSrc/src/main/kotlin/$versionsFileRelativePath")
private val versionsFileRegex = Regex("const val retroravelry = \"$semVerRegexString\"")
private val versionsFileTemplate = "const val retroravelry = \"%s\""
private val readMeFile = File("README.md")
private val readMeFileRegex = Regex("\"com.caseykulm.retroravelry:retroravelry:$semVerRegexString\"")
private val readMeFileTemplate = "\"com.caseykulm.retroravelry:retroravelry:%s\""
data class SemVer(val major: Int, val minor: Int, val mini: Int, val extra: String?)

fun SemVer.bumpMajor() = this.copy(major = major + 1)
fun SemVer.bumpMinor() = this.copy(minor = minor + 1)
fun SemVer.bumpMini() = this.copy(mini = mini + 1)
fun SemVer.stringValue(): String {
    val required = "${this.major}.${this.minor}.${this.mini}"
    val optional = "-${this.extra}"
    return required + if (extra != null) { optional } else ""
}

private val semVer: SemVer get() {
    val matchResult: MatchResult = versionsFileRegex.find(versionsFile.readText())!!
    val major: Int = matchResult.groupValues[1].toInt()
    val minor: Int = matchResult.groupValues[2].toInt()
    val mini: Int = matchResult.groupValues[3].toInt()
    val extra: String? = if (matchResult.groupValues.size == 4) matchResult.groupValues[4] else null

    return SemVer(major, minor, mini, extra)
}

private fun Project.addBumpMinorVersionTask() {
    tasks.create("bumpMinorVersionString") {
        group = project.name

        doLast {
            val newSemver = semVer.bumpMinor()
            val versionsUpdatedString = versionsFile.readText()
                .replace(versionsFileRegex, versionsFileTemplate.format(newSemver.stringValue()))
            versionsFile.writeText(versionsUpdatedString)
            val readMeUpdatedString = readMeFile.readText()
                .replace(readMeFileRegex, readMeFileTemplate.format(newSemver.stringValue()))
            readMeFile.writeText(readMeUpdatedString)
        }
    }
}

private fun Project.configureJavaLibraryPlugin() {
    configure<JavaPluginExtension> {
        withJavadocJar()
        withSourcesJar()
    }
}

private fun Project.configureMavenPlugin() {
    configure<PublishingExtension> {
        publications {
            create("maven", MavenPublication::class.java) {
                groupId = "com.caseykulm.retroravelry"
                artifactId = project.name
                version = Versions.retroravelry

                // Both kotlin and java components exist here, but only java gives us the javadoc jar and sources jar
                // from the java library plugin configured above
                from(components.getByName("java"))

                // This is a side-effect that generates a POM
                createPomConfig(project)
            }
        }
    }
}

private fun MavenPublication.createPomConfig(project: Project) = pom {
    description.set(MavenInfo.description)
    name.set(project.name)
    url.set(MavenInfo.repoUrl)

    licenses {
        license {
            name.set(MavenInfo.Licence.name)
            url.set(MavenInfo.Licence.url)
            distribution.set("repo")
        }
    }

    developers {
        developer {
            id.set(MavenInfo.Developer.id)
            name.set(MavenInfo.Developer.name)
            email.set(MavenInfo.Developer.email)
        }
    }

    scm {
        url.set(MavenInfo.versionControlUrl)
    }
}

private fun Project.configureBintrayPlugin(project: Project, secrets: RetroRavelrySecrets) {
    configure<BintrayExtension> {
        user = secrets.bintraySecrets.user
        key = secrets.bintraySecrets.key

        dryRun = false     // Whether to run this as dry-run, without deploying
        publish = true    // Whether version should be auto published after an upload
        override = false // Whether to override version artifacts already published

        setPublications("maven")

        pkg.apply { 
            repo = "maven"
            name = project.name
            userOrg = secrets.bintraySecrets.user
            desc = MavenInfo.description
            setLicenses(MavenInfo.Licence.name)
            websiteUrl = MavenInfo.repoUrl
            issueTrackerUrl = MavenInfo.issuesUrl
            vcsUrl = MavenInfo.versionControlUrl
            setLabels("ravelry", "kotlin", "java", "api")
            publicDownloadNumbers = true
            githubRepo = MavenInfo.githubRepoRelativePath
            githubReleaseNotesFile = MavenInfo.githubReleaseNotesFile

            version.apply {
                name = Versions.retroravelry
                description = MavenInfo.description
                released = Date().toString()
                vcsTag = Versions.retroravelry

                gpg.apply {
                    sign = true // Determines whether to GPG sign the files. The default is false
                    passphrase = secrets.bintraySecrets.gpgPassphrase // Optional. The passphrase for GPG signing'
                }

                mavenCentralSync.apply {
                    sync = true
                    user = secrets.sonatypeSecrets.user // OSS user token: mandatory
                    password = secrets.sonatypeSecrets.password // OSS user password: mandatory
                    close = "1" // Optional property. By default the staging repository is closed and artifacts are released to Maven Central. You can optionally turn this behaviour off (by puting 0 as value) and release the version manually.
                }
            }
        }
    }
}

private fun Project.configureJunit5() {
    tasks.named<Test>("test") {
        useJUnitPlatform()
    }
}

private fun Project.configureKapt() {
    configure<KaptExtension> {
        useBuildCache = true
    }
}