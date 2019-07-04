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
        vectorDrawables.useSupportLibrary = ApplicationConfig.supportLibraryVectorDrawables
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

androidExtensions { isExperimental = true }

dependencies {
    addCommonDependencies()
    addTestDependencies()
}
