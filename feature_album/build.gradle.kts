import ApplicationConfig.testInstrumentationRunner
import ApplicationConfig.versionCode
import ApplicationConfig.versionName
import GradlePluginId.androidFeature
import GradlePluginId.kotlinAndroid
import GradlePluginId.kotlinAndroidExtensions
import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    id(GradlePluginId.androidFeature)
    id(GradlePluginId.kotlinAndroid)
    id(GradlePluginId.kotlinAndroidExtensions)
    id(GradlePluginId.ktlintGradle)
}

android {
    compileSdkVersion(ApplicationConfig.compileSdk)

    defaultConfig {
        minSdkVersion(ApplicationConfig.minSdk)
        targetSdkVersion(ApplicationConfig.targetSdk)
        buildToolsVersion(ApplicationConfig.buildTools)

        versionCode = ApplicationConfig.versionCode
        versionName = ApplicationConfig.versionName
        testInstrumentationRunner = ApplicationConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")

        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    //module
    implementation(project(ModuleDependency.featureBase))

    //core
    implementation(LibraryDependency.kotlin)
    implementation(LibraryDependency.fuelAndroid)
    implementation(LibraryDependency.fuelCoroutines)
    implementation(LibraryDependency.fuelGson)

    //jvm
    implementation(LibraryDependency.timber)

    //android
    implementation(LibraryDependency.supportAppCompact)
    implementation(LibraryDependency.supportConstraintLayout)
    implementation(LibraryDependency.supportRecyclerView)
    implementation(LibraryDependency.supportDesign)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.0")

    //jvm test
    testImplementation(LibraryDependency.junit)

    //android test
    androidTestImplementation(LibraryDependency.testRunner)
    androidTestImplementation(LibraryDependency.espressoCore)
    androidTestImplementation(LibraryDependency.kluent)
    androidTestImplementation(LibraryDependency.kluentAndroid)
}
