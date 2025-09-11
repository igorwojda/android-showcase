package plugins

import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.adarshr.test-logger")

            tasks.withType<Test> {
                useJUnitPlatform()

                // Enable parallel test execution
                systemProperties = mapOf(
                    "junit.jupiter.execution.parallel.enabled" to "true",
                    "junit.jupiter.execution.parallel.mode.default " to "concurrent"
                )
            }

            extensions.configure<TestLoggerExtension> {
                theme = ThemeType.MOCHA
            }
        }
    }
}