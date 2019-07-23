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
        classpath(GradleDependency.ANDROID_GRADLE)
        classpath(GradleDependency.KOTLIN)
        classpath(GradleDependency.KTLINT_GRADLE)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

plugins {
    id(GradlePluginId.DETEKT) version GradlePluginVersion.DETEKT
    id(GradlePluginId.KTLINT_GRADLE) version GradlePluginVersion.KTLINT_GRADLE
    id(GradlePluginId.GRADLE_VERSION_PLUGIN) version GradlePluginVersion.GRADLE_VERSION_PLUGIN
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    plugins.apply(GradlePluginId.KTLINT_GRADLE)

    ktlint {
        version.set(CoreVersion.KTLINT)
        verbose.set(true)
        android.set(true)
    }

    plugins.apply(GradlePluginId.DETEKT)

    detekt {
        config = files("${project.rootDir}/config/detekt.yml")
        parallel = true
    }
}

// All kotlin modules
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

/**
 * This tasks run all static checks that run or CI.
 * Note that his task itself should not be used for PR checks, because here we want to run tasks sequentially,
 * while in the CI we should run tasks in parallel (for speed and separate check statuses on github)
 */
task("staticCheck") {
    afterEvaluate {
        // Get modules with "lint" task (non-android modules do not have lint task)
        val lintTasks = subprojects.mapNotNull { "${it.name}:lint" }

        // Get modules with "testDebugUnitTest" task (app module does not have it)
        val testTasks = subprojects.mapNotNull { "${it.name}:testDebugUnitTest" }
            .filter { it != "app:testDebugUnitTest" }

        // All task dependencies
        val taskDependencies =
            mutableListOf("app:assembleAndroidTest", "ktlintCheck", "detekt").also {
                it.addAll(lintTasks)
                it.addAll(testTasks)
            }

        // By defining Gradle dependency all dependent tasks will run before this "empty prCheck task"
        dependsOn(taskDependencies)
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
