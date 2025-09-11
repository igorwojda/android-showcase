import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.igorwojda.showcase.buildlogic"

// Configure the build-logic plugins to target JDK from version catalog
// This matches the JDK used to build the project, and is not related to what is running on device.
val javaVersion = libs.versions.java.get().toInt()

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(javaVersion.toString())
    }

    jvmToolchain(javaVersion)
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.spotless.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.testLogger.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.junit5.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("appConvention") {
            id = "showcase.android.application"
            implementationClass = "AppConventionPlugin"
        }
        register("featureConvention") {
            id = "showcase.android.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("libraryConvention") {
            id = "showcase.android.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("kotlinConvention") {
            id = "showcase.jvm.kotlin"
            implementationClass = "KotlinConventionPlugin"
        }
        register("testConvention") {
            id = "showcase.android.test"
            implementationClass = "TestConventionPlugin"
        }
        register("testLibraryConvention") {
            id = "showcase.android.test.library"
            implementationClass = "TestConventionLibraryPlugin"
        }
        register("spotlessConvention") {
            id = "showcase.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
        register("detektConvention") {
            id = "showcase.detekt"
            implementationClass = "DetektConventionPlugin"
        }
    }
}
