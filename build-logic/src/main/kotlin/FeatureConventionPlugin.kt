
import com.android.build.api.dsl.LibraryExtension
import config.JavaBuildConfig
import ext.debugImplementation
import ext.implementation
import ext.implementationBundle
import ext.ksp
import ext.libs2
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

                implementation(libs2.kotlin)
                implementation(libs2.core.ktx)
                implementation(libs2.timber)
                implementation(libs2.coroutines)
                implementation(libs2.material)
                implementation(libs2.compose.material)

                // Compose dependencies
                implementation(platform(libs2.compose.bom))
                implementationBundle(libs2.bundles.compose)
                debugImplementation(libs2.compose.ui.tooling)
                debugImplementation(libs2.compose.ui.test.manifest)

                // Koin
                implementation(platform(libs2.koin.bom))
                implementationBundle(libs2.bundles.koin)

                implementationBundle(libs2.bundles.retrofit)
                implementationBundle(libs2.bundles.navigation)
                implementationBundle(libs2.bundles.lifecycle)

                // Room
                implementationBundle(libs2.bundles.room)
                ksp(libs2.room.compiler)

                // Test dependencies
                testImplementation(project(":library:testUtils"))
                testImplementationBundle(libs2.bundles.test)
                testRuntimeOnly(libs2.junit.jupiter.engine)
            }
        }
    }
}
