rootProject.buildFileName = "build.gradle.kts"

include(
    ":app",
    ":feature_album",
    ":feature_profile",
    ":feature_favourite",
    ":library_test_utils"
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }

    plugins {

        id("io.gitlab.arturbosch.detekt") version "1.16.0-RC1"
        id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
        id("org.jetbrains.kotlin.jvm") version "1.4.30"
        id("org.jetbrains.kotlin.android") version "1.4.30"
        id("org.jetbrains.kotlin.android.extensions") version "1.4.30"
        id("com.android.application") version "4.1.2"
        id("com.android.library") version "4.1.2"
        id("com.android.dynamic-feature") version "4.1.2"
        id("androidx.navigation.safeargs.kotlin") version "2.3.3"
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.application",
                "com.android.library",
                "com.android.dynamic-feature" -> {
                    useModule("com.android.tools.build:gradle:4.1.2")
                }
                "androidx.navigation.safeargs.kotlin" -> {
                    useModule("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.3")
                }
            }
        }
    }
}

// Gradle 7 introduces a new way for sharing dependency versions across projects
// https://docs.gradle.org/7.0-milestone-1/userguide/platforms.html
dependencyResolutionManagement {
    versionCatalogs {

        create("libs") {
            version("okhttp", "4.9.1")

            alias("okhttp-okhttp").to("com.squareup.okhttp3", "okhttp").versionRef("okhttp")
            alias("okhttp-interceptor").to("com.squareup.okhttp3", "logging-interceptor").versionRef("okhttp")
            // bundle is basically an alias for several dependencies
            bundle("okhttp", listOf("okhttp-okhttp", "okhttp-interceptor"))


            version("kotlin", "1.4.30")
            alias("kotlin-stdlib").to("org.jetbrains.kotlin", "kotlin-stdlib").versionRef("kotlin")
            // Required by Android dynamic feature modules and SafeArgs
            alias("kotlin-reflect").to("org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
            bundle("kotlin", listOf("kotlin-stdlib", "kotlin-reflect"))
        }
    }
}
