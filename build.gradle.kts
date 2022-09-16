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
//    alias(libs.plugins.detekt)
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    }

    apply {
        plugin("org.jetbrains.kotlin.android") // or kotlin("android")
        plugin("org.jetbrains.kotlin.plugin.serialization") // or kotlin("plugin.serialization")
    }

    detekt {
        config = rootProject.files("detekt.yml")
    }
}

/*
Allows to run detekt for all files in the Gradle project and all subprojects without a need to configure detekt
plugin in every subproject.
 */
tasks.register("detektCheck", io.gitlab.arturbosch.detekt.Detekt::class) {
    val autoCorrectParam = project.hasProperty("detektAutoCorrect")

    description = "Custom detekt for to check all modules"
    parallel = true
    ignoreFailures = false
    autoCorrect = autoCorrectParam
    buildUponDefaultConfig = true
    setSource(file(projectDir))
    config.setFrom("$projectDir/detekt.yml")
    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**", "**/generated/**")

    reports {
        html.required.set(true)
        xml.required.set(true)
    }

    dependencies {
        // detekt wrapper for rules implemented by ktlint
        detektPlugins(libs.detektFormatting)
    }
}
