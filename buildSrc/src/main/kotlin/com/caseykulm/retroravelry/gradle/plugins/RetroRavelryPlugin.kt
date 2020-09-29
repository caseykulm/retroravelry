package com.caseykulm.retroravelry.gradle.plugins

import com.caseykulm.retroravelry.gradle.constants.Versions
import com.caseykulm.retroravelry.gradle.constants.MavenInfo
import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import java.io.File
import java.time.Instant

class RetroRavelryPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addUpdateReadmeTask()

        project.afterEvaluate {
            configureJavaLibraryPlugin()
            configureMavenPlugin()
            configureBintrayPlugin(project, loadRetroRavelrySecrets())
        }
    }
}

private fun Project.addUpdateReadmeTask() {
    tasks.create("updateReadmeVersion") {
        group = project.name
        project.tasks.getByName("build").dependsOn(this)

        doLast {
            val readMeFileString = File("README.md").readText()
            val readMeUpdatedString = readMeFileString.replace(Regex("\\d+.\\d+.\\d+"), Versions.retroravelry)
            File("README.md").writeText(readMeUpdatedString)
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

        dryRun = true     // Whether to run this as dry-run, without deploying
        publish = false    // Whether version should be auto published after an upload
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
                released = Instant.now().toString() // TODO Make sure this isn't running when not necessary
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

