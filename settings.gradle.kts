rootProject.buildFileName = "build.gradle.kts"

include(
    ModuleDependency.APP,
    ModuleDependency.FEATURE_ALBUM,
    ModuleDependency.FEATURE_PROFILE,
    ModuleDependency.LIBRARY_BASE,
    ModuleDependency.LIBRARY_TEST_UTILS
)
