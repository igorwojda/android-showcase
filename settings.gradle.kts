rootProject.buildFileName = "build.gradle.kts"

// Set single lock file (gradle.lockfile)
// This preview feature should be enabled by default in Gradle 7
// More: https://docs.gradle.org/current/userguide/dependency_locking.html#single_lock_file_per_project
enableFeaturePreview("ONE_LOCKFILE_PER_PROJECT")

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
        // See Dependency management section in README.md
        // https://github.com/igorwojda/android-showcase#dependency-management

        val kotlinVersion: String by settings
        id("org.jetbrains.kotlin.jvm") version kotlinVersion
        id("org.jetbrains.kotlin.android") version kotlinVersion

        val agpVersion: String by settings
        id("com.android.application") version agpVersion
        id("com.android.library") version agpVersion
        id("com.android.dynamic-feature") version agpVersion

        val navigationVersion: String by settings
        id("androidx.navigation.safeargs.kotlin") version navigationVersion

        val detektVersion: String by settings
        id("io.gitlab.arturbosch.detekt") version detektVersion

        val ktlintVersion: String by settings
        id("org.jlleitschuh.gradle.ktlint") version ktlintVersion
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

// See Dependency management section in README.md
// https://github.com/igorwojda/android-showcase#dependency-management
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {

            val kotlinVersion: String by settings
            version("kotlin", kotlinVersion)
            // Required by Android dynamic feature modules and SafeArgs
            alias("kotlin-reflect").to("org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
            version("coroutines", "1.+")
            alias("coroutines").to("org.jetbrains.kotlinx", "kotlinx-coroutines-android").versionRef("coroutines")
            bundle("kotlin", listOf("kotlin-reflect", "coroutines"))

            version("retrofit", "2.+")
            alias("retrofit-core").to("com.squareup.retrofit2", "retrofit").versionRef("retrofit")
            alias("converter-moshi").to("com.squareup.retrofit2", "converter-moshi").versionRef("retrofit")
            bundle("retrofit", listOf("retrofit-core", "converter-moshi"))

            // Retrofit will use okhttp 4 (it has binary capability with okhttp 3)
            // See: https://square.github.io/okhttp/upgrading_to_okhttp_4/
            version("okhttp", "4.+")
            alias("okhttp-okhttp").to("com.squareup.okhttp3", "okhttp").versionRef("okhttp")
            alias("okhttp-interceptor").to("com.squareup.okhttp3", "logging-interceptor").versionRef("okhttp")
            // bundle is basically an alias for several dependencies
            bundle("okhttp", listOf("okhttp-okhttp", "okhttp-interceptor"))

            version("stetho", "1.5.0") // 1.5.1 has critical bug
            alias("stetho-core").to("com.facebook.stetho", "stetho").versionRef("stetho")
            alias("stetho-okhttp3").to("com.facebook.stetho", "stetho-okhttp3").versionRef("stetho")
            bundle("stetho", listOf("stetho-core", "stetho-okhttp3"))

            version("kodein", "6.+")
            // Required by Android dynamic feature modules and SafeArgs
            alias("kodein-core").to("org.kodein.di", "kodein-di-generic-jvm").versionRef("kodein")
            alias("kodein-android-x").to("org.kodein.di", "kodein-di-framework-android-x").versionRef("kodein")
            bundle("kodein", listOf("kodein-core", "kodein-android-x"))

            alias("timber").to("com.jakewharton.timber:timber:4.+")
            alias("constraintlayout").to("androidx.constraintlayout:constraintlayout:2.+")
            alias("coordinatorlayout").to("androidx.coordinatorlayout:coordinatorlayout:1.+")
            alias("appcompat").to("androidx.appcompat:appcompat:1.+")
            alias("recyclerview").to("androidx.recyclerview:recyclerview:1.+")
            alias("material").to("com.google.android.material:material:1.+")
            alias("lottie").to("com.airbnb.android:lottie:2.+")
            alias("coil").to("io.coil-kt:coil:1.+")
            alias("play-core").to("com.google.android.play:core:1.+")

            alias("core-ktx").to("androidx.core:core-ktx:1.+")
            alias("fragment-ktx").to("androidx.fragment:fragment-ktx:1.+")
            bundle("ktx", listOf("core-ktx", "fragment-ktx"))

            version("lifecycle", "2.+")
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
            alias("junit").to("junit:junit:4.+")
            alias("test-coroutines").to("org.jetbrains.kotlinx", "kotlinx-coroutines-test").versionRef("coroutines")

            version("kluent", "1.+")
            alias("kluent-core").to("org.amshove.kluent", "kluent").versionRef("kluent")
            alias("kluent-android").to("org.amshove.kluent", "kluent-android").versionRef("kluent")

            alias("test-runner").to("androidx.test:runner:1.+")
            alias("espresso").to("androidx.test.espresso:espresso-core:3.+")
            alias("mockk").to("io.mockk:mockk:1.+")
            alias("arch").to("androidx.arch.core:core-testing:2.+")
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
