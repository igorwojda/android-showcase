plugins {
    // Convention plugins
    id("com.igorwojda.showcase.convention.detekt")
    id("com.igorwojda.showcase.convention.spotless")

    // Core Android and Kotlin plugins using version catalog
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.symbol.processing) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.test.logger) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.junit5.android) apply false
}

// Exclude konsistTest from general test tasks e.g. ./gradlew testDebugUnitTest
subprojects {
    if (name == "konsistTest") {
        tasks.matching { it.name.startsWith("test") }.configureEach {
            onlyIf {
                gradle.startParameter.taskRequests.any { request ->
                    request.args.any { arg ->
                        arg.contains("konsistTest") || arg.contains(":test") && arg.startsWith(":konsistTest")
                    }
                }
            }
        }
    }
}
