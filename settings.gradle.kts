rootProject.name = "android-showcase"

include(
    ":app",
    ":feature:album",
    ":feature:settings",
    ":feature:favourite",
    ":feature:base",
    ":library:testUtils",
    ":konsistTest",
)

pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application") version "8.13.0" apply false
    id("com.android.library") version "8.13.0" apply false
    id("org.jetbrains.kotlin.android") version "2.2.20" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.2.20" apply false
    id("com.google.devtools.ksp") version "2.2.20-2.0.2" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20" apply false
    id("com.adarshr.test-logger") version "4.0.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8" apply false
    id("com.diffplug.spotless") version "7.2.1" apply false
    id("de.mannodermaus.android-junit5") version "1.13.4.0" apply false
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        // Added for testing local Konsist artifacts
        mavenLocal()
        mavenCentral()
    }
}

// Generate type safe accessors when referring to other projects eg.
// Before: implementation(project(":feature_album"))
// After: implementation(projects.featureAlbum)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
