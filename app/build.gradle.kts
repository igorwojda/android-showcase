import ApplicationConfig.versionCode
import ApplicationConfig.versionName
import GradlePluginId.androidApplication
import GradlePluginId.kotlinAndroid
import GradlePluginId.kotlinAndroidExtensions
import com.android.build.gradle.AndroidConfig
import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    id(GradlePluginId.androidApplication)
    id(GradlePluginId.kotlinAndroid)
    id(GradlePluginId.kotlinAndroidExtensions)
    id(GradlePluginId.ktlintGradle)
}

android {
    compileSdkVersion(ApplicationConfig.compileSdk)

    defaultConfig {
        applicationId = ApplicationConfig.id
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
//    implementation(project(ModuleDependency.featureArtist))

    //core
    implementation(LibraryDependency.kotlin)

    //android
    implementation(LibraryDependency.supportAppCompact)
    implementation(LibraryDependency.supportConstraintLayout)
    implementation(LibraryDependency.supportRecyclerView)
    implementation(LibraryDependency.supportDesign)

    //jvm test
    testImplementation(LibraryDependency.junit)

    //android test
    androidTestImplementation(LibraryDependency.testRunner)
    androidTestImplementation(LibraryDependency.espressoCore)
}
