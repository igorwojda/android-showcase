package plugins

import com.android.build.api.dsl.ApplicationExtension
import config.JavaBuildConfig
import ext.libs
import ext.versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("kotlin-convention-plugin")
                apply("spotless-convention-plugin")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<ApplicationExtension> {
                compileSdk = versions.compile.sdk.get().toInt()

                defaultConfig {
                    applicationId = "com.igorwojda.showcase"
                    minSdk = versions.min.sdk.get().toInt()
                    targetSdk = versions.target.sdk.get().toInt()
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
                    resources.excludes += setOf(
                        "META-INF/AL2.0",
                        "META-INF/licenses/**",
                        "**/attach_hotspot_windows.dll",
                        "META-INF/LGPL2.1"
                    )
                }

                testOptions {
                    unitTests.isReturnDefaultValues = true
                }

                lint {
                    baseline = file("android-lint-baseline.xml")
                }
            }

            dependencies {
                add("implementation", libs.kotlin)
                add("implementation", libs.core.ktx)
                add("implementation", libs.timber)
                add("implementation", libs.coroutines)
                add("implementation", libs.material)
                add("implementation", libs.compose.material)

                // Compose dependencies
                add("implementation", platform(libs.compose.bom))
                add("implementation", libs.tooling.preview)
                add("debugImplementation", libs.compose.ui.tooling)
                add("debugImplementation", libs.compose.ui.test.manifest)
                add("implementation", libs.navigation.compose)

                // Koin
                add("implementation", platform(libs.koin.bom))
                add("implementation", libs.bundles.koin)

                add("implementation", libs.bundles.retrofit)
                add("implementation", libs.bundles.navigation)
                add("implementation", libs.bundles.lifecycle)
            }
        }
    }
}