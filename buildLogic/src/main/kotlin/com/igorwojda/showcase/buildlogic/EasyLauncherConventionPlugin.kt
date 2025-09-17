package com.igorwojda.showcase.buildlogic

import com.project.starter.easylauncher.filter.ChromeLikeFilter
import com.project.starter.easylauncher.plugin.EasyLauncherExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("detekt.LongMethod")
class EasyLauncherConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.starter.easylauncher")
            }

            extensions.configure<EasyLauncherExtension> {
                defaultFlavorNaming(true)

                buildTypes.create("debug") {
                    setFilters(
                        chromeLike(
                            ribbonColor = OVERLAY_COLOR_BACKGROUND_DEBUG,
                            labelColor = OVERLAY_COLOR_TEXT,
                            gravity = ChromeLikeFilter.Gravity.BOTTOM,
                            overlayHeight = OVERLAY_HEIGHT,
                            textSizeRatio = 0.2F,
                        ),
                    )
                }
            }
        }
    }

    companion object {
        private const val OVERLAY_COLOR_BACKGROUND_DEBUG = "#99AD0000"
        private const val OVERLAY_COLOR_TEXT = "#FFFFFF"
        private const val OVERLAY_HEIGHT = 0.25F
    }
}
