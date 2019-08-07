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
        classpath(GradleDependency.SAFE_ARGS)
        classpath(GradleDependency.KTLINT_GRADLE)
    }
}

plugins {
    id(GradlePluginId.DETEKT) version GradlePluginVersion.DETEKT
    id(GradlePluginId.KTLINT_GRADLE) version GradlePluginVersion.KTLINT_GRADLE
    id(GradlePluginId.GRADLE_VERSION_PLUGIN) version GradlePluginVersion.GRADLE_VERSION_PLUGIN
}

// all projects = root project + sub projects
allprojects {
    repositories {
        google()
        jcenter()
    }
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

// JVM target applied to all Kotlin tasks across all sub-projects
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
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

task("staticCheck") {
    description = """Mimics all static checks that run on CI.
        Note that this task is intended to run locally (not on CI), because on CI we prefer to have parallel execution 
        and separate reports for each check (multiple statuses eg. on github PR page).
    """.trimMargin()

    group = "check"
    afterEvaluate {
        // Filter modules with "lintDebug" task (non-Android modules do not have lintDebug task)
        val lintTasks = subprojects.mapNotNull { "${it.name}:lintDebug" }

        // Get modules with "testDebugUnitTest" task (app module does not have it)
        val testTasks = subprojects.mapNotNull { "${it.name}:testDebugUnitTest" }
            .filter { it != "app:testDebugUnitTest" }

        // All task dependencies
        val taskDependencies =
            mutableListOf("app:assembleAndroidTest", "ktlintCheck", "detekt").also {
                it.addAll(lintTasks)
                it.addAll(testTasks)
            }

        // By defining Gradle dependency all dependent tasks will run before this "empty" task
        dependsOn(taskDependencies)
    }
}
