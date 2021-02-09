rootProject.buildFileName = "build.gradle.kts"

include(
    ":app",
    ":feature_album",
    ":feature_profile",
    ":feature_favourite",
    ":library_test_utils"
)

// Gradle is missing proper build-in mechanism to share dependency versions between:
// - library dependency and gradle plugin dependency (eg. kotlin, navigation)
// - implementation and test implementation of the library (eg. coroutines)
// More: https://github.com/gradle/gradle/issues/16077
//
// As a result some versions are defined multiple times.
// To avoid defining dependency version multiple times dependencies are defined in the gradle.properties file and
// retrieved using settings delegate. Unfortunately this technique cannot be applied to all versions, so some will
// remain duplicated.

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }

    plugins {
        val kotlinVersion: String by settings
        val agpVersion: String by settings
        val navigationVersion: String by settings

        id("io.gitlab.arturbosch.detekt") version "1.16.0-RC1"
        id("org.jlleitschuh.gradle.ktlint") version "9.4.1"
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.jetbrains.kotlin.android") version kotlinVersion
        id("com.android.application") version agpVersion
        id("com.android.library") version agpVersion
        id("com.android.dynamic-feature") version agpVersion
        id("androidx.navigation.safeargs.kotlin") version navigationVersion
    }

    resolutionStrategy {
        val agpCoordinates: String by settings
        val navigationCoordinates: String by settings

        eachPlugin {
            when (requested.id.id) {
                "com.android.application",
                "com.android.library",
                "com.android.dynamic-feature" -> {
                    // Version should be retrieved from "val agpVersion: String by settings" delegate, but
                    // Gradle does not allow it.
                    useModule(agpCoordinates) // agpVersion
                }
                "androidx.navigation.safeargs.kotlin" -> {
                    // Version should be retrieved from "val navigationVersion: String by settings" delegate, but
                    // Gradle does not allow it.
                    useModule(navigationCoordinates) // navigationVersion
                }
            }
        }
    }
}

// Version catalogs is the new Gradle 7 way for sharing dependency versions across projects.
// https://docs.gradle.org/7.0-milestone-1/userguide/platforms.html
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            // Retrofit will use okhttp 4 (it has binary capability with okhttp 3)
            // See: https://square.github.io/okhttp/upgrading_to_okhttp_4/
            version("okhttp", "4.9.1")
            alias("okhttp-okhttp").to("com.squareup.okhttp3", "okhttp").versionRef("okhttp")
            alias("okhttp-interceptor").to("com.squareup.okhttp3", "logging-interceptor").versionRef("okhttp")
            // bundle is basically an alias for several dependencies
            bundle("okhttp", listOf("okhttp-okhttp", "okhttp-interceptor"))

            val kotlinVersion: String by settings
            version("kotlin", kotlinVersion)
            //
            // alias("kotlin-stdlib").to("org.jetbrains.kotlin", "kotlin-stdlib").versionRef("kotlin")
            // Required by Android dynamic feature modules and SafeArgs
            alias("kotlin-reflect").to("org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
            version("coroutines", "1.3.9")
            alias("coroutines").to("org.jetbrains.kotlinx", "kotlinx-coroutines-android").versionRef("coroutines")
            bundle("kotlin", listOf("kotlin-reflect", "coroutines"))

            version("retrofit", "2.9.0")
            alias("retrofit-core").to("com.squareup.retrofit2", "retrofit").versionRef("retrofit")
            alias("converter-moshi").to("com.squareup.retrofit2", "converter-moshi").versionRef("retrofit")
            bundle("retrofit", listOf("retrofit-core", "converter-moshi"))

            alias("play-core").to("com.google.android.play:core:1.9.1")

            version("stetho", "1.5.0")
            alias("stetho-core").to("com.facebook.stetho", "stetho").versionRef("stetho")
            alias("stetho-okhttp3").to("com.facebook.stetho", "stetho-okhttp3").versionRef("stetho")
            bundle("stetho", listOf("stetho-core", "stetho-okhttp3"))

            version("kodein", "6.5.5")
            // Required by Android dynamic feature modules and SafeArgs
            alias("kodein-core").to("org.kodein.di", "kodein-di-generic-jvm").versionRef("kodein")
            alias("kodein-android-x").to("org.kodein.di", "kodein-di-framework-android-x").versionRef("kodein")
            bundle("kodein", listOf("kodein-core", "kodein-android-x"))

            alias("timber").to("com.jakewharton.timber:timber:4.7.1")
            alias("constraintlayout").to("androidx.constraintlayout:constraintlayout:2.0.4")
            alias("coordinatorlayout").to("androidx.coordinatorlayout:coordinatorlayout:1.1.0")
            alias("appcompat").to("androidx.appcompat:appcompat:1.2.0")
            alias("recyclerview").to("androidx.recyclerview:recyclerview:1.1.0")
            alias("material").to("com.google.android.material:material:1.2.0")
            alias("lottie").to("com.airbnb.android:lottie:2.5.0")
            alias("coil").to("io.coil-kt:coil:1.1.1")

            alias("core-ktx").to("androidx.core:core-ktx:1.3.2")
            alias("fragment-ktx").to("androidx.fragment:fragment-ktx:1.2.5")
            bundle("ktx", listOf("core-ktx", "fragment-ktx"))

            version("lifecycle", "2.2.0")
            alias("viewmodel-ktx").to("androidx.lifecycle", "lifecycle-viewmodel-ktx").versionRef("lifecycle")
            alias("livedata-ktx").to("androidx.lifecycle", "lifecycle-livedata-ktx").versionRef("lifecycle")
            alias("lifecycle-common").to("androidx.lifecycle", "lifecycle-common-java8").versionRef("lifecycle")
            bundle("lifecycle", listOf("viewmodel-ktx", "livedata-ktx", "lifecycle-common"))

            val navigationVersion: String by settings
            version("navigation", navigationVersion)
            alias("navigation-fragment").to("androidx.navigation", "navigation-fragment-ktx").versionRef("navigation")
            alias("navigation-dynamic")
                .to("androidx.navigation", "navigation-dynamic-features-fragment")
                .versionRef("navigation")
            alias("navigation-ui-ktx").to("androidx.navigation", "navigation-ui-ktx").versionRef("navigation")
            bundle("navigation", listOf("navigation-fragment", "navigation-dynamic", "navigation-ui-ktx"))

            // Test dependencies
            alias("junit").to("junit:junit:4.13")
            alias("test-coroutines").to("org.jetbrains.kotlinx", "kotlinx-coroutines-test").versionRef("coroutines")
            version("kluent", "1.65")
            alias("kluent-core").to("org.amshove.kluent", "kluent").versionRef("kluent")
            alias("kluent-android").to("org.amshove.kluent", "kluent-android").versionRef("kluent")
            alias("test-runner").to("com.android.support.test:runner:1.0.2")
            alias("espresso").to("com.android.support.test.espresso:espresso-core:3.1.0")
            alias("mockk").to("io.mockk:mockk:1.10.5")
            alias("arch").to("androidx.arch.core:core-testing:2.1.0")
            bundle(
                "test",
                listOf(
                    "junit",
                    "test-coroutines",
                    "kluent-core",
                    "kluent-android",
                    "test-runner",
                    "espresso",
                    "mockk",
                    "arch"
                )
            )
        }
    }
}
