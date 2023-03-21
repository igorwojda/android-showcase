rootProject.name = "android-showcase"

include(
    ":app",
    ":feature_album",
    ":feature_profile",
    ":feature_favourite",
    ":feature_base",
    ":library_test_utils",
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {

    repositories {
        google()
        mavenCentral()

        // Accompanist snahshoots (https://github.com/google/accompanist)
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

// Generate type safe accessors when referring to other projects eg.
// Before: implementation(project(":feature_album"))
// After: implementation(projects.featureAlbum)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("kotlin", "1.8.10")
            // KSP depends on specific Kotlin version, so it must be upgraded together with Kotlin
            // https://repo.maven.apache.org/maven2/com/google/devtools/ksp/symbol-processing-gradle-plugin/
            version("kotlinSymbolProcessing", "1.8.10-1.0.9")
            // Compose compiler depends on specific Kotlin version, so it must be upgraded together with Kotlin
            // https://developer.android.com/jetpack/androidx/releases/compose-kotlin
            version("kotlinCompilerExtensionVersion", "1.4.2")
            version("navigation", "2.5.3")
            version("testLogger", "3.2.0")
            version("coroutines", "1.6.4")
            version("retrofit", "2.9.0")
            version("okhttp", "4.10.0")
            version("koin", "3.3.3")
            version("coil", "2.2.2")
            // verison 2.6.0-alpha01 has collectAsStateWithLifecycle
            // https://issuetracker.google.com/issues/230557927?hl=id
            version("lifecycle", "2.6.0-alpha03")
            version("room", "2.5.0")
            version("serializationJson", "1.5.0")
            version("kotlinxSerializationConverter", "0.8.0")
            version("viewBindingPropertyDelegate", "1.5.8")
            version("timber", "5.0.1")
            version("constraintLayout", "2.1.4")
            version("appCompat", "1.6.1")
            version("recyclerView", "1.3.0")
            version("compose", "1.3.3")
            version("materialCompose", "1.0.1")
            version("material", "1.8.0")
            version("lottie", "6.0.0")
            version("playCore", "1.10.3")
            version("coreKtx", "1.9.0")
            version("fragmentKtx", "1.5.5")
            // Info https://google.github.io/accompanist/flowlayout/
            // Repo https://oss.sonatype.org/content/repositories/snapshots/com/google/accompanist/accompanist-flowlayout/
            version("accompanistFlowLayout", "0.28.0")
            version("spotless", "6.17.0")
            version("detekt", "1.22.0")
            version("androidGradlePlugin", "7.4.2")
            version("junit", "5.9.2")
            version("androidJUnit5", "1.8.2.1")
            version("kluent", "1.72")
            version("testRunner", "1.5.2")
            version("mockk", "1.13.4")
            version("espresso", "3.5.1")
            version("coreTesting", "2.2.0")

            // Gradle Plugins https://plugins.gradle.org/
            plugin("android-application", "com.android.application").versionRef("androidGradlePlugin")
            plugin("android-library", "com.android.library").versionRef("androidGradlePlugin")
            plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
            plugin("kotlin-android", "org.jetbrains.kotlin.android").versionRef("kotlin")
            plugin("kotlin-serialization", "org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")
            plugin("spotless", "com.diffplug.spotless").versionRef("spotless")
            plugin("detekt", "io.gitlab.arturbosch.detekt").versionRef("detekt")
            plugin("safeArgs", "androidx.navigation.safeargs.kotlin").versionRef("navigation")
            plugin("junit5Android", "de.mannodermaus.android-junit5").versionRef("androidJUnit5")
            plugin("testLogger", "com.adarshr.test-logger").versionRef("testLogger")
            plugin("kotlin-symbolProcessing", "com.google.devtools.ksp").versionRef("kotlinSymbolProcessing")

            library("kotlin", "org.jetbrains.kotlin", "kotlin-reflect").versionRef("kotlin")
            library("coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-android").versionRef("coroutines")
            library("retrofitCore", "com.squareup.retrofit2", "retrofit").versionRef("retrofit")
            library(
                "kotlinxSerializationConverter",
                "com.jakewharton.retrofit",
                "retrofit2-kotlinx-serialization-converter",
            ).versionRef("kotlinxSerializationConverter")
            library("serializationJson", "org.jetbrains.kotlinx", "kotlinx-serialization-json").versionRef("serializationJson")
            // Retrofit will use okhttp 4 (it has binary capability with okhttp 3)
            // See: https://square.github.io/okhttp/upgrading_to_okhttp_4/
            library("okhttp", "com.squareup.okhttp3", "okhttp").versionRef("okhttp")
            library("okhttpInterceptor", "com.squareup.okhttp3", "logging-interceptor").versionRef("okhttp")
            library("koin", "io.insert-koin", "koin-android").versionRef("koin")
            library("koinNavigation", "io.insert-koin", "koin-androidx-navigation").versionRef("koin")
            library(
                "viewBindingPropertyDelegate",
                "com.github.kirich1409",
                "viewbindingpropertydelegate",
            ).versionRef("viewBindingPropertyDelegate")
            library("timber", "com.jakewharton.timber", "timber").versionRef("timber")
            library("constraintLayout", "androidx.constraintlayout", "constraintlayout").versionRef("constraintLayout")
            library("appCompat", "androidx.appcompat", "appcompat").versionRef("appCompat")
            library("recyclerView", "androidx.recyclerview", "recyclerview").versionRef("recyclerView")
            library("composeMaterial", "androidx.compose.material3", "material3").versionRef("materialCompose")
            library("material", "com.google.android.material", "material").versionRef("material")
            library("lottie", "com.airbnb.android", "lottie-compose").versionRef("lottie")
            library("coil", "io.coil-kt", "coil-compose").versionRef("coil")
            library("playCore", "com.google.android.play", "core").versionRef("playCore")
            library("coreKtx", "androidx.core", "core-ktx").versionRef("coreKtx")
            library("fragmentKtx", "androidx.fragment", "fragment-ktx").versionRef("fragmentKtx")
            library("viewmodelKtx", "androidx.lifecycle", "lifecycle-viewmodel-ktx").versionRef("lifecycle")
            library("livedataKtx", "androidx.lifecycle", "lifecycle-livedata-ktx").versionRef("lifecycle")
            library("composeUI", "androidx.compose.ui", "ui").versionRef("compose")
            library("toolingPreview", "androidx.compose.ui", "ui-tooling-preview").versionRef("compose")
            library("livedataRuntime", "androidx.lifecycle", "lifecycle-runtime-compose").versionRef("lifecycle")
            library("navigationFragment", "androidx.navigation", "navigation-fragment-ktx").versionRef("navigation")
            library("navigationUiKtx", "androidx.navigation", "navigation-ui-ktx").versionRef("navigation")
            library("roomKtx", "androidx.room", "room-ktx").versionRef("room")
            library("roomRuntime", "androidx.room", "room-runtime").versionRef("room")
            library("roomCompiler", "androidx.room", "room-compiler").versionRef("room")
            library("accompanistFlowLayout", "com.google.accompanist", "accompanist-flowlayout").versionRef("accompanistFlowLayout")
            library("junit", "junit", "junit").versionRef("junit")
            library("testCoroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-test").versionRef("coroutines")
            library("kluent", "org.amshove.kluent", "kluent-android").versionRef("kluent")
            library("testRunner", "androidx.test", "runner").versionRef("testRunner")
            library("espresso", "androidx.test.espresso", "espresso-core").versionRef("espresso")
            library("mockk", "io.mockk", "mockk").versionRef("mockk")
            library("coreTesting", "androidx.arch.core", "core-testing").versionRef("coreTesting")
            library("junitJupiterApi", "org.junit.jupiter", "junit-jupiter-api").versionRef("junit")
            library("junitJupiterEngine", "org.junit.jupiter", "junit-jupiter-engine").versionRef("junit")

            bundle("retrofit", listOf("retrofitCore", "kotlinxSerializationConverter", "serializationJson", "okhttp", "okhttpInterceptor"))
            bundle("koin", listOf("koin", "koinNavigation"))
            bundle("compose", listOf("composeUI", "toolingPreview", "lottie", "coil"))
            bundle("lifecycle", listOf("viewmodelKtx", "livedataKtx", "livedataRuntime"))
            bundle("navigation", listOf("navigationFragment", "navigationUiKtx"))
            bundle("room", listOf("roomKtx", "roomRuntime"))
            bundle(
                "test",
                listOf(
                    "testCoroutines",
                    "kluent",
                    "testRunner",
                    "espresso",
                    "mockk",
                    "coreTesting",
                    "junitJupiterApi",
                    "junitJupiterEngine",
                ),
            )
        }
    }
}
