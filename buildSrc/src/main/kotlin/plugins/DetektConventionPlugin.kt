package plugins

import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Plugin
import org.gradle.api.Project

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            repositories.mavenCentral()

            val detektCheck = tasks.register("detektCheck", Detekt::class.java) {
                description = "Checks that sourcecode satisfies detekt rules."
                autoCorrect = false
            }

            val detektApply = tasks.register("detektApply", Detekt::class.java) {
                description = "Applies code formatting rules to sourcecode in-place."
                autoCorrect = true
            }

            listOf(detektCheck, detektApply).forEach { taskProvider ->
                taskProvider.configure {
                    group = "verification"
                    parallel = true
                    ignoreFailures = false
                    setSource(file(rootDir))

                    // Custom detekt config
                    config.setFrom("$rootDir/detekt.yml")

                    // Use default configuration on top of custom config
                    buildUponDefaultConfig = true

                    // Runs detekt for all files in the Gradle project and all subprojects
                    include("**/*.kt", "**/*.kts")
                    exclude("**/resources/**", "**/build/**", "**/generated/**")

                    reports {
                        html.required.set(true)
                        xml.required.set(true)
                    }
                }
            }
        }
    }
}