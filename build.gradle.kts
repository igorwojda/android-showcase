@Suppress("DSL_SCOPE_VIOLATION") // Because of IDE bug https://youtrack.jetbrains.com/issue/KTIJ-19370
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.safeArgs) apply false
    alias(libs.plugins.junit5Android) apply false
    alias(libs.plugins.detekt)
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
    setSource(file(projectDir))
    // Detekt config is composed of two configs:
    // 1. detekt default config rules
    // https://github.com/detekt/detekt/blob/main/detekt-core/src/main/resources/default-detekt-config.yml
    // 2. detekt-formatting rules
    // https://github.com/detekt/detekt/blob/main/detekt-formatting/src/main/resources/config/config.yml

    // Custom detekt config
    config.setFrom("$projectDir/detekt.yml")

    // Use default configuration on top of custom config (new detect rules will work out of the box after upgrading detekt version)
    buildUponDefaultConfig = true

    include("**/*.kt", "**/*.kts")
    exclude("**/resources/**", "**/build/**", "**/generated/**")

    reports {
        html.required.set(true)
        xml.required.set(true)
    }

    dependencies {
        // detekt wrapper for rules implemented by ktlint https://detekt.dev/docs/rules/formatting
        detektPlugins(libs.detektFormatting)
    }
}
