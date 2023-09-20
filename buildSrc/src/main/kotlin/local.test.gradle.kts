import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    id("org.gradle.jvm-test-suite")
    id("com.adarshr.test-logger")
}

tasks.withType<Test> {
    useJUnitPlatform()

    // Enable parallel test execution
    systemProperties = mapOf(
        "junit.jupiter.execution.parallel.enabled" to "true",
        "junit.jupiter.execution.parallel.mode.default " to "concurrent",
    )
}

testlogger {
    theme = ThemeType.MOCHA
}
