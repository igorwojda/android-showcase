pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    // Using the plugins DSL allows generating type-safe accessors for Kotlin DSL
    plugins {
        // Variables retrieved by settings delegate are defined in gradle.properties file
        // See dependency management section in README.md
        // https://github.com/igorwojda/android-showcase#dependency-management
        val agpVersion: String by settings
        id("com.android.application") version agpVersion
        id("com.android.library") version agpVersion

        val kotlinVersion: String by settings
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        kotlin("android") version kotlinVersion

        val kspVersion: String by settings
        id("com.google.devtools.ksp") version kspVersion

        val navigationVersion: String by settings
        id("androidx.navigation.safeargs.kotlin") version navigationVersion

        val detektVersion: String by settings
        id("io.gitlab.arturbosch.detekt") version detektVersion

        val ktlintGradleVersion: String by settings
        id("org.jlleitschuh.gradle.ktlint") version ktlintGradleVersion

        val androidJUnit5Version: String by settings
        id("de.mannodermaus.android-junit5") version androidJUnit5Version
    }
}
dependencyResolutionManagement {
    // TODO
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "android-showcase"

include(
    ":app",
    ":feature_album",
    ":feature_profile",
    ":feature_favourite",
    ":feature_base",
    ":library_test_utils"
)

// See Dependency management section in README.md
// https://github.com/igorwojda/android-showcase#dependency-management
@Suppress("detekt.StringLiteralDuplication")
dependencyResolutionManagement {
    // TODO
    versionCatalogs {
        create("libs") {

            val kotlinVersion: String by settings
            version("kotlin", kotlinVersion)
            // Required by Android dynamic feature modules and SafeArgs
            library("kotlin-reflect", "org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
            version("coroutines", "1.+")
            library("coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-android").versionRef("coroutines")
            bundle("kotlin", listOf("kotlin-reflect", "coroutines"))

            version("retrofit", "2.+")
            library("retrofit-core", "com.squareup.retrofit2", "retrofit").versionRef("retrofit")
            library("converter-moshi", "com.squareup.retrofit2", "converter-moshi").versionRef("retrofit")
            bundle("retrofit", listOf("retrofit-core", "converter-moshi"))

            // Retrofit will use okhttp 4 (it has binary capability with okhttp 3)
            // See: https://square.github.io/okhttp/upgrading_to_okhttp_4/
            version("okhttp", "4.+")
            library("okhttp-okhttp", "com.squareup.okhttp3", "okhttp").versionRef("okhttp")
            library("okhttp-interceptor", "com.squareup.okhttp3", "logging-interceptor").versionRef("okhttp")
            bundle("okhttp", listOf("okhttp-okhttp", "okhttp-interceptor"))

            version("koin", "3.+")
            library("koin-android", "io.insert-koin", "koin-android").versionRef("koin")
            bundle("koin", listOf("koin-android"))

            library("viewbindingpropertydelegate", "com.github.kirich1409:viewbindingpropertydelegate:1.+")

            library("timber", "com.jakewharton.timber:timber:4.+")
            library("constraintLayout", "androidx.constraintlayout:constraintlayout:2.+")
            library("coordinatorLayout", "androidx.coordinatorlayout:coordinatorlayout:1.+")
            library("appcompat", "androidx.appcompat:appcompat:1.+")
            library("recyclerview", "androidx.recyclerview:recyclerview:1.+")
            library("material", "com.google.android.material:material:1.+")
            library("lottie", "com.airbnb.android:lottie:+")
            library("coil", "io.coil-kt:coil:+")
            library("play-core", "com.google.android.play:core:1.+")

            library("core-ktx", "androidx.core:core-ktx:1.+")
            library("fragment-ktx", "androidx.fragment:fragment-ktx:1.+")
            bundle("ktx", listOf("core-ktx", "fragment-ktx"))

            version("lifecycle", "2.+")
            library("viewmodel-ktx", "androidx.lifecycle", "lifecycle-viewmodel-ktx").versionRef("lifecycle")
            library("livedata-ktx", "androidx.lifecycle", "lifecycle-livedata-ktx").versionRef("lifecycle")
            library("lifecycle-common", "androidx.lifecycle", "lifecycle-common-java8").versionRef("lifecycle")
            bundle("lifecycle", listOf("viewmodel-ktx", "livedata-ktx", "lifecycle-common"))

            val navigationVersion: String by settings
            version("navigation", navigationVersion)
            library("navigation-fragment", "androidx.navigation", "navigation-fragment-ktx").versionRef("navigation")
            library("navigation-dynamic",
                "androidx.navigation",
                "navigation-dynamic-features-fragment").versionRef("navigation")
            library("navigation-ui-ktx", "androidx.navigation", "navigation-ui-ktx").versionRef("navigation")
            bundle("navigation", listOf("navigation-fragment", "navigation-dynamic", "navigation-ui-ktx"))

            version("room", "2.+")
            library("room-ktx", "androidx.room", "room-ktx").versionRef("room")
            library("room-runtime", "androidx.room", "room-runtime").versionRef("room")
            bundle("room", listOf("room-ktx", "room-runtime"))

            library("room.compiler", "androidx.room", "room-compiler").versionRef("room")

            // Test dependencies
            library("test-coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-test").versionRef("coroutines")

            version("kluent", "1.+")
            library("kluent-android", "org.amshove.kluent", "kluent-android").versionRef("kluent")

            library("test-runner", "androidx.test:runner:1.+")
            library("espresso", "androidx.test.espresso:espresso-core:3.+")
            library("mockk", "io.mockk:mockk:1.+")
            library("core-testing", "androidx.arch.core:core-testing:2.+")
            library("livedata-testing", "com.jraska.livedata:testing-ktx:1.+")

            version("junit", "5.+")
            library("junit-jupiter-api", "org.junit.jupiter", "junit-jupiter-api").versionRef("junit")

            bundle(
                "test",
                listOf(
                    "test-coroutines",
                    "kluent-android",
                    "test-runner",
                    "espresso",
                    "mockk",
                    "core-testing",
                    "junit-jupiter-api",
                    "livedata-testing"
                )
            )

            library("junit-jupiter-engine", "org.junit.jupiter", "junit-jupiter-engine").versionRef("junit")
        }
    }
}
