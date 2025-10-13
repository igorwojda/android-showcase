package com.igorwojda.showcase.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project

class AboutLibrariesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.mikepenz.aboutlibraries.plugin.android")
            }
        }
    }
}
