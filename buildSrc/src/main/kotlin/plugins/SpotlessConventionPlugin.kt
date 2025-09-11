package plugins

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.diffplug.spotless")

            extensions.configure<SpotlessExtension> {
                kotlin {
                    target("**/*.kt", "**/*.kts")
                    targetExclude("**/buildSrc/build/**/*.*")
                    ktlint()
                    endWithNewline()
                }

                // Don't add spotless as dependency for the Gradle's check task to facilitate separated codebase checks
                isEnforceCheck = false
            }
        }
    }
}