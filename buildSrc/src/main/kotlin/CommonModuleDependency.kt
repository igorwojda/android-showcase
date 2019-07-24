import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/*
Define common dependencies, so they can be easily updated across feature modules
 */
fun DependencyHandler.addCommonDependencies() {
    api(LibraryDependency.KOTLIN)
    api(LibraryDependency.KODEIN)
    api(LibraryDependency.KODEIN_ANDROID_X)
    api(LibraryDependency.RETROFIT)
    api(LibraryDependency.RETROFIT_MOSHI_CONVERTER)
    api(LibraryDependency.STETHO)
    api(LibraryDependency.STETHO_OK_HTTP)
    api(LibraryDependency.TIMBER)
    api(LibraryDependency.APP_COMPACT)
    api(LibraryDependency.SUPPORT_CONSTRAINT_LAYOUT)
    api(LibraryDependency.RECYCLER_VIEW)
    api(LibraryDependency.SUPPORT_MATERIAL)
    api(LibraryDependency.COROUTINES_ANDROID)
    api(LibraryDependency.LIFECYCLE_EXTENSIONS)
    api(LibraryDependency.COORDINATOR_LAYOUT)
    api(LibraryDependency.CORE_KTX)
    api(LibraryDependency.FRAGMENT_KTX)
    api(LibraryDependency.LIFECYCLE_VIEW_MODEL_KTX)
    api(LibraryDependency.PICASSO)
    api(LibraryDependency.CUSTOM_FLOATING_ACTION_BUTTON)
    api(LibraryDependency.K_ANDROID)
}

fun DependencyHandler.addTestDependencies() {
    testImplementation(project(ModuleDependency.LIBRARY_TEST_UTILS))

    testImplementation(TestLibraryDependency.JUNIT)
    androidTestImplementation(TestLibraryDependency.TEST_RUNNER)
    androidTestImplementation(TestLibraryDependency.ESPRESSO_CORE)
    testImplementation(TestLibraryDependency.KLUENT)
    androidTestImplementation(TestLibraryDependency.KLUENT_ANDROID)
    testImplementation(TestLibraryDependency.MOCKITO_INLINE)
    androidTestImplementation(TestLibraryDependency.MOCKITO_ANDROID)
    testImplementation(TestLibraryDependency.MOCKITO_KOTLIN)
    testImplementation(TestLibraryDependency.COROUTINES_TEST)
}

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL syntax in module\build.gradle.kts files.
 */
private fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

private fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

private fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

private fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

private fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

private fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency =

    uncheckedCast(
        project(
            if (configuration != null) mapOf("path" to path, "configuration" to configuration)
            else mapOf("path" to path)
        )
    )

@Suppress("unchecked_cast", "nothing_to_inline")
private inline fun <T> uncheckedCast(obj: Any?): T =
    obj as T
