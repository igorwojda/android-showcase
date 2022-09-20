rootProject.name = "android-showcase"

include(
    ":app",
    ":feature_album",
    ":feature_profile",
    ":feature_favourite",
    ":feature_base",
    ":library_test_utils"
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
    }
}

// Generate type safe accessors when referring to other projects eg.
// Before: implementation(project(":feature_album"))
// After: implementation(projects.featureAlbum)
// enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
