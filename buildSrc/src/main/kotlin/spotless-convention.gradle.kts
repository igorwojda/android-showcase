import org.jetbrains.kotlin.gradle.internal.builtins.StandardNames.FqNames.target

plugins {
    id("com.diffplug.spotless")
}

spotless {
    kotlin {
        target("**/*.kt", "**/*.kts")
        targetExclude("**/buildSrc/build/**/*.*")
        ktlint()
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task to facilitate separated codebase checks
    isEnforceCheck = false
}
