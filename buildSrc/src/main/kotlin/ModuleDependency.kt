import kotlin.reflect.full.memberProperties

const val FEATURE_PREFIX = ":feature_"

// "Module" means "project" in terminology of Gradle API. To be specific each "Android module" is a Gradle "subproject".

// enum class ModuleDependency(val moduleName: String) {
//     APP(":app"),
//     FEATURE_ALBUM(":feature_album"),
//     FEATURE_PROFILE(":feature_profile"),
//     FEATURE_FAVOURITE(":feature_favourite"),
//     LIBRARY_BASE(":library_base"),
//     LIBRARY_TEST_UTILS(":library_test_utils");
//
//     // There is no easy way to retrieve dynamic feature modules from gradle based on plugin type ("com.android.dynamic-feature",
//     // so we have to retrieve them from enum
//     fun getDynamicFeatures() = enumValues<ModuleDependency>().filter { it.moduleName.startsWith(FEATURE_PREFIX) }
//
//     fun getAll() = enumValues<ModuleDependency>()
// }

object ModuleDependency {
    const val APP = ":app"
    const val FEATURE_ALBUM = ":feature_album"
    const val FEATURE_PROFILE = ":feature_profile"
    const val FEATURE_FAVOURITE = ":feature_favourite"
    const val LIBRARY_BASE = ":library_base"
    const val LIBRARY_TEST_UTILS = ":library_test_utils"

    val a = ModuleDependency::class.memberProperties.forEach { println(it) }
    // map { it.getter.call(ModuleDependency::class.objectInstance).toString() }

    val dynamicFeatureModules = setOf(
        ModuleDependency.FEATURE_ALBUM,
        ModuleDependency.FEATURE_FAVOURITE,
        ModuleDependency.FEATURE_PROFILE
    )

    val modules = setOf(
        ModuleDependency.FEATURE_ALBUM,
        ModuleDependency.FEATURE_FAVOURITE,
        ModuleDependency.FEATURE_PROFILE
    )
}
