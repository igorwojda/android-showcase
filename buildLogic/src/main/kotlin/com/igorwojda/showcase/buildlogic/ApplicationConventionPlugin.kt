package com.igorwojda.showcase.buildlogic

import com.android.build.api.dsl.ApplicationExtension
import com.igorwojda.showcase.buildlogic.config.JavaBuildConfig
import com.igorwojda.showcase.buildlogic.ext.debugImplementation
import com.igorwojda.showcase.buildlogic.ext.excludeLicenseAndMetaFiles
import com.igorwojda.showcase.buildlogic.ext.implementation
import com.igorwojda.showcase.buildlogic.ext.libs
import com.igorwojda.showcase.buildlogic.ext.versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("detekt.LongMethod")
class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply<KotlinConventionPlugin>()
                apply<SpotlessConventionPlugin>()
                apply<AboutLibrariesConventionPlugin>()
            }

            extensions.configure<ApplicationExtension> {
                compileSdk =
                    versions
                        .compile
                        .sdk
                        .get()
                        .toInt()

                defaultConfig {
                    applicationId = "com.igorwojda.showcase"

                    minSdk =
                        versions
                            .min
                            .sdk
                            .get()
                            .toInt()

                    targetSdk =
                        versions
                            .target
                            .sdk
                            .get()
                            .toInt()

                    versionCode = 1
                    versionName = "1.0"
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    multiDexEnabled = true

                    vectorDrawables {
                        useSupportLibrary = true
                    }
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

                packaging {
                    excludeLicenseAndMetaFiles()
                }

                testOptions {
                    unitTests.isReturnDefaultValues = true
                }
            }

            dependencies {
                implementation(libs.kotlin.reflect)
                implementation(libs.core.ktx)
                implementation(libs.timber)
                implementation(libs.coroutines)
                implementation(libs.material.material)
                implementation(libs.compose.material)
                implementation(libs.material.icons)

                // Compose dependencies
                implementation(platform(libs.compose.bom))
                implementation(libs.tooling.preview)
                debugImplementation(libs.compose.ui.tooling)
                debugImplementation(libs.compose.ui.test.manifest)
                implementation(libs.navigation.compose)

                // Koin
                implementation(platform(libs.koin.bom))
                implementation(libs.bundles.koin)

                implementation(libs.bundles.retrofit)
                implementation(libs.viewmodel.ktx)
                implementation(libs.core.splashscreen)
            }
        }
    }
}
