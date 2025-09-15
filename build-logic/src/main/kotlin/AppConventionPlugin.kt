import com.android.build.api.dsl.ApplicationExtension
import config.JavaBuildConfig
import ext.debugImplementation
import ext.implementation
import ext.implementationBundle
import ext.libs
import ext.libs2
import ext.versions2
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("detekt.LongMethod")
class AppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.igorwojda.showcase.buildlogic.kotlin")
                apply("com.igorwojda.showcase.buildlogic.spotless")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            libs2.kotlin

            extensions.configure<ApplicationExtension> {
                compileSdk = versions2
                    .compile
                    .sdk
                    .get()
                    .toInt()

                defaultConfig {
                    applicationId = "com.igorwojda.showcase"

                    minSdk = versions2
                            .min
                            .sdk
                            .get()
                            .toInt()

                    targetSdk = versions2
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
                    resources.excludes +=
                        setOf(
                            "META-INF/AL2.0",
                            "META-INF/licenses/**",
                            "**/attach_hotspot_windows.dll",
                            "META-INF/LGPL2.1",
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
                implementation(libs.findLibrary("kotlin").get())
                implementation(libs.findLibrary("core-ktx").get())
                implementation(libs.findLibrary("timber").get())
                implementation(libs.findLibrary("coroutines").get())
                implementation(libs.findLibrary("material").get())
                implementation(libs.findLibrary("compose-material").get())

                // Compose dependencies
                implementation(platform(libs.findLibrary("compose-bom").get()))
                implementation(libs.findLibrary("tooling-preview").get())
                debugImplementation(libs.findLibrary("compose-ui-tooling").get())
                debugImplementation(libs.findLibrary("compose-ui-test-manifest").get())
                implementation(libs.findLibrary("navigation-compose").get())

                // Koin
                implementation(platform(libs.findLibrary("koin-bom").get()))
                implementationBundle(libs.findBundle("koin").get())

                implementationBundle(libs.findBundle("retrofit").get())
                implementationBundle(libs.findBundle("navigation").get())
                implementationBundle(libs.findBundle("lifecycle").get())
            }
        }
    }
}
