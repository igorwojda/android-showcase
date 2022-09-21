// Top-level build file where you can add configuration options common to all sub-projects/modules.

// Android-Junit5 plugin does not support Gradle plugins DSL https://github.com/mannodermaus/android-junit5/issues/283
buildscript {
    dependencies {
        classpath(libs.junit5AndroidPlugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.symbolProcessing)
    alias(libs.plugins.safeArgs) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.testLogger)
}

subprojects {
    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
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
