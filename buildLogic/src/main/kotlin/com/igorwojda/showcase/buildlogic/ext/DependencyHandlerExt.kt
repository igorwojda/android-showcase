package com.igorwojda.showcase.buildlogic.ext

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(provider: Provider<out Any>) {
    add("implementation", provider)
}

fun DependencyHandlerScope.implementation(project: Project) {
    add("implementation", project)
}

fun DependencyHandlerScope.implementation(provider: LibrariesForLibs.KotlinLibraryAccessors) {
    add("implementation", provider)
}

fun DependencyHandlerScope.debugImplementation(provider: Provider<out Any>) {
    add("debugImplementation", provider)
}

fun DependencyHandlerScope.ksp(provider: Provider<out Any>) {
    add("ksp", provider)
}

fun DependencyHandlerScope.testImplementation(project: Project) {
    add("testImplementation", project)
}

fun DependencyHandlerScope.testImplementation(provider: Provider<out Any>) {
    add("testImplementation", provider)
}

fun DependencyHandlerScope.testRuntimeOnly(provider: Provider<out Any>) {
    add("testRuntimeOnly", provider)
}
