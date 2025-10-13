package com.igorwojda.showcase.buildlogic

import com.mikepenz.aboutlibraries.plugin.AboutLibrariesExtension
import com.mikepenz.aboutlibraries.plugin.DuplicateMode
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AboutLibrariesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.mikepenz.aboutlibraries.plugin.android")
            }

            extensions.configure<AboutLibrariesExtension> {
                library {
                    // Avoids duplicate entries in the generated about libraries screen
                    duplicationMode.set(DuplicateMode.MERGE)
                }

                collect {
                    all.set(true)
                }
            }
        }
    }
}
