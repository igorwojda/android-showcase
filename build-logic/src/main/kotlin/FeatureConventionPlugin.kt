
import com.android.build.api.dsl.LibraryExtension
import config.JavaBuildConfig
import ext.debugImplementation
import ext.implementation
import ext.implementationBundle
import ext.ksp
import ext.libs
import ext.testImplementation
import ext.testImplementationBundle
import ext.testRuntimeOnly
import ext.versions2
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("detekt.LongMethod")
class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("com.igorwojda.showcase.buildlogic.kotlin")
                apply("com.igorwojda.showcase.buildlogic.test")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = versions2
                    .compile
                    .sdk
                    .get()
                    .toInt()

                defaultConfig {
                    minSdk = versions2
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
                    resources.excludes +=
                        setOf(
                            "META-INF/AL2.0",
                            "META-INF/licenses/**",
                            "**/attach_hotspot_windows.dll",
                            "META-INF/LGPL2.1",
                        )
                }
            }

            dependencies {
                // Add feature:base dependency only for non-base feature modules
                if (project.path != ":feature:base") {
                    implementation(project(":feature:base"))
                }

                implementation(libs.findLibrary("kotlin").get())
                implementation(libs.findLibrary("core-ktx").get())
                implementation(libs.findLibrary("timber").get())
                implementation(libs.findLibrary("coroutines").get())
                implementation(libs.findLibrary("material").get())
                implementation(libs.findLibrary("compose-material").get())

                // Compose dependencies
                implementation(platform(libs.findLibrary("compose-bom").get()))
                implementationBundle(libs.findBundle("compose").get())
                debugImplementation(libs.findLibrary("compose-ui-tooling").get())
                debugImplementation(libs.findLibrary("compose-ui-test-manifest").get())

                // Koin
                implementation(platform(libs.findLibrary("koin-bom").get()))
                implementationBundle(libs.findBundle("koin").get())

                implementationBundle(libs.findBundle("retrofit").get())
                implementationBundle(libs.findBundle("navigation").get())
                implementationBundle(libs.findBundle("lifecycle").get())

                // Room
                implementationBundle(libs.findBundle("room").get())
                ksp(libs.findLibrary("room-compiler").get())

                // Test dependencies
                testImplementation(project(":library:testUtils"))
                testImplementationBundle(libs.findBundle("test").get())
                testRuntimeOnly(libs.findLibrary("junit-jupiter-engine").get())
            }
        }
    }
}
