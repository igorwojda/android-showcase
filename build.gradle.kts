// TODO: update desc
// Top-level build file where you can add configuration options common to all sub-projects/modules.
// plugins and dependencies are defined in the version catalog  file
// Utilising Gradle dependency management

plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.android") apply false // or kotlin("android")
    id("org.jetbrains.kotlin.plugin.serialization") apply false // or kotlin("plugin.serialization")
    id("com.google.devtools.ksp") apply false
    id("io.gitlab.arturbosch.detekt")
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    apply {
        plugin("io.gitlab.arturbosch.detekt")
    }

    detekt {
        config = rootProject.files("detekt.yml")
    }
}
