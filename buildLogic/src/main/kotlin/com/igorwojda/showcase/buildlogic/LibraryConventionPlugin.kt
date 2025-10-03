package com.igorwojda.showcase.buildlogic

import com.android.build.api.dsl.LibraryExtension
import com.igorwojda.showcase.buildlogic.config.JavaBuildConfig
import com.igorwojda.showcase.buildlogic.ext.excludeLicenseAndMetaFiles
import com.igorwojda.showcase.buildlogic.ext.versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply<KotlinConventionPlugin>()
                apply<TestConventionPlugin>()
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension> {
                compileSdk =
                    versions
                        .compile
                        .sdk
                        .get()
                        .toInt()

                defaultConfig {
                    minSdk =
                        versions
                            .min
                            .sdk
                            .get()
                            .toInt()

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }

                buildFeatures {
                    viewBinding = true
                    buildConfig = true
                    compose = true
                }

                compileOptions {
                    sourceCompatibility = JavaBuildConfig.JAVA_VERSION
                    targetCompatibility = JavaBuildConfig.JAVA_VERSION
                }

                testOptions {
                    unitTests.isReturnDefaultValues = true
                }

                packaging {
                    excludeLicenseAndMetaFiles()
                }
            }
        }
    }
}
