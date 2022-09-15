// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.android") apply false // or kotlin("android")
    id("com.google.devtools.ksp") apply false

    alias(libs.plugins.safeArgs) apply false
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.detekt)
    alias(libs.plugins.testLogger)
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    apply {
        plugin("io.gitlab.arturbosch.detekt")
        plugin("org.jetbrains.kotlin.android") //or kotlin("android")
        plugin("org.jetbrains.kotlin.plugin.serialization") // or kotlin("plugin.serialization")
    }

    detekt {
        config = rootProject.files("detekt.yml")
    }
}
