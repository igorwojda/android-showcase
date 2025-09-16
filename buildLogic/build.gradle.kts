import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.igorwojda.showcase.buildlogic"

/*
Configure the buildLogic plugins to target JDK from version catalog
This matches the JDK used to build the project, and is not related to what is running on device.
*/
val javaVersion =
    libs
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
    implementation(libs.test.logger.gradlePlugin)
    implementation(libs.compose.gradlePlugin)
    implementation(libs.junit5.gradlePlugin)
    implementation(libs.easy.launcher.gradlePlugin)

    /*
    Expose generated type-safe version catalogs accessors accessible from precompiled script plugins
    e.g. add("implementation", libs.koin)
    https://github.com/gradle/gradle/issues/15383
     */
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("applicationConvention") {
            id = "com.igorwojda.showcase.convention.application"
            implementationClass = "com.igorwojda.showcase.buildlogic.ApplicationConventionPlugin"
        }

        register("featureConvention") {
            id = "com.igorwojda.showcase.convention.feature"
            implementationClass = "com.igorwojda.showcase.buildlogic.FeatureConventionPlugin"
        }

        register("libraryConvention") {
            id = "com.igorwojda.showcase.convention.library"
            implementationClass = "com.igorwojda.showcase.buildlogic.LibraryConventionPlugin"
        }

        register("kotlinConvention") {
            id = "com.igorwojda.showcase.convention.kotlin"
            implementationClass = "com.igorwojda.showcase.buildlogic.KotlinConventionPlugin"
        }

        register("testConvention") {
            id = "com.igorwojda.showcase.convention.test"
            implementationClass = "com.igorwojda.showcase.buildlogic.TestConventionPlugin"
        }

        register("testLibraryConvention") {
            id = "com.igorwojda.showcase.convention.test.library"
            implementationClass = "com.igorwojda.showcase.buildlogic.TestConventionLibraryPlugin"
        }

        register("spotlessConvention") {
            id = "com.igorwojda.showcase.convention.spotless"
            implementationClass = "com.igorwojda.showcase.buildlogic.SpotlessConventionPlugin"
        }

        register("detektConvention") {
            id = "com.igorwojda.showcase.convention.detekt"
            implementationClass = "com.igorwojda.showcase.buildlogic.DetektConventionPlugin"
        }

        register("easyLauncherConvention") {
            id = "com.igorwojda.showcase.convention.easylauncher"
            implementationClass = "com.igorwojda.showcase.buildlogic.EasyLauncherConventionPlugin"
        }
    }
}
