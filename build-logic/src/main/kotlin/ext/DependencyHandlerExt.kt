package ext

import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(provider: Provider<MinimalExternalModuleDependency>) {
    add("implementation", provider)
}

fun DependencyHandlerScope.implementation(provider: Project) {
    add("implementation", provider)
}

fun DependencyHandlerScope.implementationBundle(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("implementation", bundle)
}

fun DependencyHandlerScope.debugImplementation(provider: Provider<MinimalExternalModuleDependency>) {
    add("debugImplementation", provider)
}

fun DependencyHandlerScope.ksp(provider: Provider<MinimalExternalModuleDependency>) {
    add("ksp", provider)
}

fun DependencyHandlerScope.testImplementation(provider: Provider<MinimalExternalModuleDependency>) {
    add("testImplementation", provider)
}

fun DependencyHandlerScope.testImplementationBundle(bundle: Provider<ExternalModuleDependencyBundle>) {
    add("testImplementation", bundle)
}

fun DependencyHandlerScope.testImplementation(provider: Project) {
    add("testImplementation", provider)
}

fun DependencyHandlerScope.testRuntimeOnly(provider: Provider<MinimalExternalModuleDependency>) {
    add("testRuntimeOnly", provider)
}