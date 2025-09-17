package com.igorwojda.showcase.buildlogic.ext

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

private const val IMPLEMENTATION = "implementation"
private const val DEBUG_IMPLEMENTATION = "debugImplementation"
private const val TEST_IMPLEMENTATION = "testImplementation"
private const val TEST_RUNTIME_ONLY = "testRuntimeOnly"
private const val KSP = "ksp"

fun DependencyHandlerScope.implementation(provider: Provider<out Any>) {
    add(IMPLEMENTATION, provider)
}

fun DependencyHandlerScope.implementation(project: Project) {
    add(IMPLEMENTATION, project)
}

fun DependencyHandlerScope.implementation(provider: LibrariesForLibs.KotlinLibraryAccessors) {
    add(IMPLEMENTATION, provider)
}

fun DependencyHandlerScope.debugImplementation(provider: Provider<out Any>) {
    add(DEBUG_IMPLEMENTATION, provider)
}

fun DependencyHandlerScope.ksp(provider: Provider<out Any>) {
    add(KSP, provider)
}

fun DependencyHandlerScope.testImplementation(project: Project) {
    add(TEST_IMPLEMENTATION, project)
}

fun DependencyHandlerScope.testImplementation(provider: Provider<out Any>) {
    add(TEST_IMPLEMENTATION, provider)
}

fun DependencyHandlerScope.testRuntimeOnly(provider: Provider<out Any>) {
    add(TEST_RUNTIME_ONLY, provider)
}
