package plugins

import com.android.build.api.dsl.LibraryExtension
import config.JavaBuildConfig
import ext.libs
import ext.versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-convention-plugin")
                apply("test-convention-plugin")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = versions.compile.sdk.get().toInt()

                defaultConfig {
                    minSdk = versions.min.sdk.get().toInt()
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
                    resources.excludes += setOf(
                        "META-INF/AL2.0",
                        "META-INF/licenses/**",
                        "**/attach_hotspot_windows.dll",
                        "META-INF/LGPL2.1"
                    )
                }
            }

            dependencies {
                // Add feature:base dependency only for non-base feature modules
                if (project.path != ":feature:base") {
                    add("implementation", project(":feature:base"))
                }

                add("implementation", libs.kotlin)
                add("implementation", libs.core.ktx)
                add("implementation", libs.timber)
                add("implementation", libs.coroutines)
                add("implementation", libs.material)
                add("implementation", libs.compose.material)

                // Compose dependencies
                add("implementation", platform(libs.compose.bom))
                add("debugImplementation", libs.compose.ui.tooling)
                add("debugImplementation", libs.compose.ui.test.manifest)

                // Koin
                add("implementation", platform(libs.koin.bom))
                add("implementation", libs.bundles.koin)

                add("implementation", libs.bundles.retrofit)
                add("implementation", libs.bundles.navigation)
                add("implementation", libs.bundles.lifecycle)

                // Room
                add("implementation", libs.bundles.room)
                add("ksp", libs.room.compiler)

                add("implementation", platform(libs.compose.bom))
                add("implementation", libs.bundles.compose)

                // Test dependencies
                add("testImplementation", project(":library:testUtils"))
                add("testImplementation", libs.bundles.test)
                add("testRuntimeOnly", libs.junit.jupiter.engine)
            }
        }
    }
}