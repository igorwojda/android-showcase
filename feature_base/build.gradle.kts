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
    compileSdkVersion(ApplicationConfig.compileSdkVersion)

    defaultConfig {
        minSdkVersion(ApplicationConfig.minSdkVersion)
        targetSdkVersion(ApplicationConfig.targetSdkVersion)
        buildToolsVersion(ApplicationConfig.buildToolsVersion)

        versionCode = ApplicationConfig.versionCode
        versionName = ApplicationConfig.versionName
        testInstrumentationRunner = ApplicationConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")

        }

        getByName("debug") {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = TestOptions.isReturnDefaultValues
    }
}

dependencies {
    //core
    implementation(LibraryDependency.kotlin)
    implementation(LibraryDependency.kodein)
    implementation(LibraryDependency.kodeinAndroidX)
    implementation(LibraryDependency.retrofit)
    implementation(LibraryDependency.retrofitMoshiConverter)
    implementation(LibraryDependency.retrofitCoroutineAdapter)
    implementation(LibraryDependency.stetho)
    implementation(LibraryDependency.stethoOkHttp)
    implementation(LibraryDependency.timber)
    implementation(LibraryDependency.appCompact)
    implementation(LibraryDependency.supportConstraintLayout)
    implementation(LibraryDependency.recyclerView)
    implementation(LibraryDependency.supportMaterial)
    implementation(LibraryDependency.coroutinesAndroid)
    implementation(LibraryDependency.lifecycleExtensions)
    kapt(LibraryDependency.lifecycleCompiler)
    implementation(LibraryDependency.coordinatorLayout)
    implementation(LibraryDependency.coreKtx)
    implementation(LibraryDependency.fragmentKtx)
    implementation(LibraryDependency.lifecycleViewModelKtx)
    implementation(LibraryDependency.picasso)
    implementation(LibraryDependency.customFloatingActionButton)
    implementation(LibraryDependency.kAndroid)

    //test
    testImplementation(LibraryDependency.junit)
    androidTestImplementation(LibraryDependency.testRunner)
    androidTestImplementation(LibraryDependency.espressoCore)
    testImplementation(LibraryDependency.kluent)
    androidTestImplementation(LibraryDependency.kluentAndroid)
    testImplementation(LibraryDependency.mockitoInline)
    androidTestImplementation(LibraryDependency.mockitoAndroid)
    testImplementation(LibraryDependency.mockitoKotlin)
}
