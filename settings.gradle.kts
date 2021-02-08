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
