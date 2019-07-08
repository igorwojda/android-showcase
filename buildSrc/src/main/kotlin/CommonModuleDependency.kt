import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/*
Define common dependencies, so they can be easily updated across feature modules
 */
fun DependencyHandler.addCommonDependencies() {
    implementation(LibraryDependency.kotlin)
    implementation(LibraryDependency.kodein)
    implementation(LibraryDependency.kodeinAndroidX)
    implementation(LibraryDependency.retrofit)
    implementation(LibraryDependency.retrofitMoshiConverter)
    implementation(LibraryDependency.stetho)
    implementation(LibraryDependency.stethoOkHttp)
    implementation(LibraryDependency.timber)
    implementation(LibraryDependency.appCompact)
    implementation(LibraryDependency.supportConstraintLayout)
    implementation(LibraryDependency.recyclerView)
    implementation(LibraryDependency.supportMaterial)
    implementation(LibraryDependency.coroutinesAndroid)
    implementation(LibraryDependency.lifecycleExtensions)
    implementation(LibraryDependency.coordinatorLayout)
    implementation(LibraryDependency.coreKtx)
    implementation(LibraryDependency.fragmentKtx)
    implementation(LibraryDependency.lifecycleViewModelKtx)
    implementation(LibraryDependency.picasso)
    implementation(LibraryDependency.customFloatingActionButton)
    implementation(LibraryDependency.kAndroid)
}

fun DependencyHandler.addTestDependencies() {
    testImplementation(project(ModuleDependency.libraryTestUtils))

    testImplementation(TestLibraryDependency.junit)
    androidTestImplementation(TestLibraryDependency.testRunner)
    androidTestImplementation(TestLibraryDependency.espressoCore)
    testImplementation(TestLibraryDependency.kluent)
    androidTestImplementation(TestLibraryDependency.kluentAndroid)
    testImplementation(TestLibraryDependency.mockitoInline)
    androidTestImplementation(TestLibraryDependency.mockitoAndroid)
    testImplementation(TestLibraryDependency.mockitoKotlin)
    testImplementation(TestLibraryDependency.coroutinesTest)
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
