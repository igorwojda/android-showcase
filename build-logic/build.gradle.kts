import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.igorwojda.showcase.buildlogic"

/*
Configure the build-logic plugins to target JDK from version catalog
This matches the JDK used to build the project, and is not related to what is running on device.
*/
val javaVersion = libs
    .versions
    .java
    .get()


kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(javaVersion)
    }

    jvmToolchain(javaVersion.toInt())
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
            id = "com.igorwojda.showcase.buildlogic.application"
            implementationClass = "AppConventionPlugin"
        }
        register("featureConvention") {
            id = "com.igorwojda.showcase.buildlogic.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("libraryConvention") {
            id = "com.igorwojda.showcase.buildlogic.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("kotlinConvention") {
            id = "com.igorwojda.showcase.buildlogic.kotlin"
            implementationClass = "KotlinConventionPlugin"
        }
        register("testConvention") {
            id = "com.igorwojda.showcase.buildlogic.test"
            implementationClass = "TestConventionPlugin"
        }
        register("testLibraryConvention") {
            id = "com.igorwojda.showcase.buildlogic.android.test.library"
            implementationClass = "TestConventionLibraryPlugin"
        }
        register("spotlessConvention") {
            id = "com.igorwojda.showcase.buildlogic.spotless"
            implementationClass = "SpotlessConventionPlugin"
        }
        register("detektConvention") {
            id = "com.igorwojda.showcase.buildlogic.detekt"
            implementationClass = "DetektConventionPlugin"
        }
    }
}
