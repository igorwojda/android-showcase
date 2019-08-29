import kotlin.reflect.full.memberProperties

private const val FEATURE_PREFIX = ":feature_"

// "Module" means "project" in terminology of Gradle API. To be specific each "Android module" is a Gradle "subproject"
object ModuleDependency {
    const val APP = ":app"
    const val FEATURE_ALBUM = ":feature_album"
    const val FEATURE_PROFILE = ":feature_profile"
    const val FEATURE_FAVOURITE = ":feature_favourite"
    const val LIBRARY_BASE = ":library_base"
    const val LIBRARY_TEST_UTILS = ":library_test_utils"

    // Unused function false positive
    // See: https://youtrack.jetbrains.com/issue/KT-33610
    fun getAllModules() = ModuleDependency::class.memberProperties
        .filter { it.isConst }
        .map { it.getter.call().toString() }
        .toSet()

    fun getDynamicFeatureModules() = getAllModules()
        .filter { it.startsWith(FEATURE_PREFIX) }
        .toSet()
}
