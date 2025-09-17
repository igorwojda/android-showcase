package com.igorwojda.showcase.buildlogic

import com.diffplug.gradle.spotless.SpotlessExtension
import com.igorwojda.showcase.buildlogic.ext.libs
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

                    val customRuleSets =
                        listOf(
                            libs.ktlint.ruleset.standard,
                            libs.nlopez.compose.rules,
                            libs.twitter.compose.rules,
                        ).map {
                            it.get().toString()
                        }

                    ktlint()
                        .customRuleSets(customRuleSets)

                    endWithNewline()
                }

                // Don't add spotless as dependency for the Gradle's check task to facilitate separated codebase checks
                isEnforceCheck = false
            }
        }
    }
}
