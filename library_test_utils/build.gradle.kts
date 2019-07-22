plugins {
    id(GradlePluginId.androidLibrary)
    id(GradlePluginId.kotlinAndroid)
    id(GradlePluginId.kotlinAndroidExtensions)
    id(GradlePluginId.ktlintGradle)
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)

    defaultConfig {
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.targetSdkVersion)

        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
        vectorDrawables.useSupportLibrary = AndroidConfig.supportLibraryVectorDrawables
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
    implementation(LibraryDependency.kotlin)
    implementation(TestLibraryDependency.junit)
    implementation(TestLibraryDependency.coroutinesTest)
}
