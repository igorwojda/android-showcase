import com.android.build.gradle.BaseExtension
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(GradlePluginId.DETEKT)
    id(GradlePluginId.KTLINT_GRADLE)
    id(GradlePluginId.ANDROID_JUNIT_5) apply false
    id(GradlePluginId.KOTLIN_ANDROID) apply false
    id(GradlePluginId.ANDROID_APPLICATION) apply false
    id(GradlePluginId.ANDROID_DYNAMIC_FEATURE) apply false
    id(GradlePluginId.ANDROID_LIBRARY) apply false
    id(GradlePluginId.SAFE_ARGS) apply false
}

// all projects = root project + sub projects
allprojects {
    repositories {
        google()
        jcenter()
    }

    // We want to apply ktlint at all project level because it also checks Gradle config files (*.kts)
    apply(plugin = GradlePluginId.KTLINT_GRADLE)

    // Ktlint configuration for sub-projects
    ktlint {
        // Version of ktlint cmd tool (Ktlint Gradle plugin is just a wrapper for this tool)
        version.set("0.40.0")
        verbose.set(true)
        android.set(true)

        // Uncomment below line and run .\gradlew ktlintCheck to see check ktlint experimental rules
        // enableExperimentalRules.set(true)

        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        }

        filter {
            exclude { element -> element.file.path.contains("generated/") }
        }
    }

    // Gradle dependency locking - lock all configurations of the app
    // More: https://docs.gradle.org/current/userguide/dependency_locking.html
    dependencyLocking {
        lockAllConfigurations()
    }
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    apply(plugin = GradlePluginId.DETEKT)

    detekt {
        config = files("$rootDir/detekt.yml")

        parallel = true

        // By default detekt does not check test source set and gradle specific files, so hey have to be added manually
        input = files(
            "$rootDir/buildSrc",
            "$rootDir/build.gradle.kts",
            "$rootDir/settings.gradle.kts",
            "src/main/kotlin",
            "src/test/kotlin"
        )
    }

    afterEvaluate {
        configureAndroid()
    }

    // While writing versions locks pre-release version of dependencies will be ignored
    configurations.all {
        resolutionStrategy.componentSelection {
            // Accept the highest version matching the requested version that isn't...
            all {
                // detekt is using pre-release dependencies
                val detektExceptions = listOf(
                    "io.gitlab.arturbosch.detekt",
                    "com.fasterxml.jackson",
                    "com.fasterxml.jackson.core",
                    "com.fasterxml.jackson"
                )

                if (detektExceptions.any { it == candidate.group }) {
                    return@all
                }

                // android lint is using pre-release dependencies
                val androidLintExceptions = listOf("com.android.tools.build")

                if (androidLintExceptions.any { it == candidate.group }) {
                    return@all
                }

                // Do reject pre-release version
                val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview")
                    .any { Regex("(?i).*[.-]$it[.\\d-]*").matches(candidate.version) }

                if (rejected) {
                    reject("Release candidate")
                }
            }
        }
    }
}

fun Project.configureAndroid() {
    (project.extensions.findByName("android") as? BaseExtension)?.run {
        sourceSets {
            map { it.java.srcDir("src/${it.name}/kotlin") }
        }
    }
}

// JVM target applied to all Kotlin tasks across all sub-projects
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

// Target version of the generated JVM bytecode. It is used for type resolution.
tasks.withType<Detekt> {
    this.jvmTarget = "1.8"
}
