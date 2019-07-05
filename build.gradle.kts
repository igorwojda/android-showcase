import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        // Android plugin & support libraries
        google()

        // Main open-source repository
        jcenter()

        // Ktlint Gradle
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(GradleDependency.androidGradle)
        classpath(GradleDependency.kotlin)
        classpath(GradleDependency.ktlintGradle)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

plugins {
    id(GradlePluginId.detekt) version GradlePluginVersion.detekt
    id(GradlePluginId.ktlintGradle) version GradlePluginVersion.ktlintGradle
    id(GradlePluginId.gradleVersionPlugin) version GradlePluginVersion.gradleVersionPlugin
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    plugins.apply(GradlePluginId.ktlintGradle)

    ktlint {
        version.set(CoreVersion.ktlint)
        verbose.set(true)
        android.set(true)
    }

    plugins.apply(GradlePluginId.detekt)

    detekt {
        config = files("${project.rootDir}/config/detekt.yml")
        parallel = true
    }
}

// All kotlin modules
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

task("staticCheck") {
    afterEvaluate {
        val lintDependencies = subprojects.mapNotNull { "${it.name}:lint" }
        dependsOn(lintDependencies + listOf("ktlintCheck", "detekt"))
    }
}

tasks {
    // Gradle versions plugin configuration
    "dependencyUpdates"(DependencyUpdatesTask::class) {
        resolutionStrategy {
            componentSelection {
                all {
                    // Do not show pre-release version of library in generated dependency report
                    val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview")
                        .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
                        .any { it.matches(candidate.version) }
                    if (rejected) {
                        reject("Release candidate")
                    }

                    // kAndroid newest version is 0.8.8 (jcenter), however maven repository contains version 0.8.7 and
                    // plugin fails to recognize it correctly
                    if (candidate.group == "com.pawegio.kandroid") {
                        reject("version ${candidate.version} is broken for ${candidate.group}'")
                    }
                }
            }
        }
    }
}
