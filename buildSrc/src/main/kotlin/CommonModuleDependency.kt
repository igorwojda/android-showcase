import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/*
Define common dependencies, so they can be easily updated across feature modules
 */
fun DependencyHandler.addCommonDependencies() {
    implementation(LibraryDependency.KOTLIN)
    implementation(LibraryDependency.KODEIN)
    implementation(LibraryDependency.KODEIN_ANDROID_X)
    implementation(LibraryDependency.RETROFIT)
    implementation(LibraryDependency.RETROFIT_MOSHI_CONVERTER)
    implementation(LibraryDependency.STETHO)
    implementation(LibraryDependency.STETHO_OK_HTTP)
    implementation(LibraryDependency.TIMBER)
    implementation(LibraryDependency.APP_COMPACT)
    implementation(LibraryDependency.SUPPORT_CONSTRAINT_LAYOUT)
    implementation(LibraryDependency.RECYCLER_VIEW)
    implementation(LibraryDependency.SUPPORT_MATERIAL)
    implementation(LibraryDependency.COROUTINES_ANDROID)
    implementation(LibraryDependency.LIFECYCLE_EXTENSIONS)
    implementation(LibraryDependency.COORDINATOR_LAYOUT)
    implementation(LibraryDependency.CORE_KTX)
    implementation(LibraryDependency.FRAGMENT_KTX)
    implementation(LibraryDependency.LIFECYCLE_VIEW_MODEL_KTX)
    implementation(LibraryDependency.PICASSO)
    implementation(LibraryDependency.CUSTOM_FLOATING_ACTION_BUTTON)
    implementation(LibraryDependency.K_ANDROID)
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
