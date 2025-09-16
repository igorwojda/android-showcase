package com.igorwojda.showcase.buildlogic

import com.igorwojda.showcase.buildlogic.config.JavaBuildConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            kotlinExtension.jvmToolchain(JavaBuildConfig.JVM_TOOLCHAIN_VERSION)

            tasks.withType<KotlinCompile>().configureEach {
                compilerOptions {
                    freeCompilerArgs.addAll(
                                    // Fix for https://youtrack.jetbrains.com/issue/KT-73255: set default annotation target
                        "-Xannotation-default-target=param-property"
                    )
                }
            }
        }
    }
}
