rootProject.name = "android-showcase"

include(
    ":app",
    ":feature:album",
    ":feature:profile",
    ":feature:favourite",
    ":feature:base",
    ":lib:testUtils",
    ":konsistTest",
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
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
