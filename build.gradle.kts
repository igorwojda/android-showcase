// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.symbolProcessing)
    alias(libs.plugins.safeArgs) apply false
    alias(libs.plugins.detekt)
//    alias(libs.plugins.jUnit5Android)
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
