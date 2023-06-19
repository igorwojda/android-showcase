plugins {
    id("com.diffplug.spotless")
}

spotless {
    kotlin {
        target("**/*.kt", "**/*.kts")
        targetExclude("**/buildSrc/build/**/*.*")
        ktlint()

        indentWithSpaces()
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task to facilitate separated codebase checks
    isEnforceCheck = false
}
