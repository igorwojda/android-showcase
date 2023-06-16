plugins {
    id("de.mannodermaus.android-junit5")
    id("com.adarshr.test-logger")
}

tasks.withType<Test> {
    useJUnitPlatform()

    // Enable parallel test execution
    systemProperties = mapOf(
        "junit.jupiter.execution.parallel.enabled" to "true",
        "junit.jupiter.execution.parallel.mode.default " to "concurrent",
    )

    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
}
