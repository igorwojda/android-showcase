import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.safeArgs) apply false
    alias(libs.plugins.detekt)
    id("local.spotless")
}

val detektCheck by tasks.registering(Detekt::class) {
    description = "Checks that sourcecode satisfies detekt rules."
    autoCorrect = false
}

val detektApply by tasks.registering(Detekt::class) {
    description = "Applies code formatting rules to sourcecode in-place."
    autoCorrect = true
}

configure(listOf(detektCheck, detektApply)) {
    configure {
        group = "verification"
        parallel = true
        ignoreFailures = false
        setSource(file(projectDir))

        // Custom detekt config
        config.setFrom("$projectDir/detekt.yml")

        // Use default configuration on top of custom config
        // (new detect rules will work out of the box after upgrading detekt version)
        buildUponDefaultConfig = true

        /*
        Runs detekt for all files in the Gradle project and all subprojects without
        a need to configure detekt plugin in every subproject.
         */
        include("**/*.kt", "**/*.kts")
        exclude("**/resources/**", "**/build/**", "**/generated/**")

        reports {
            html.required.set(true)
            xml.required.set(true)
        }
    }
}
