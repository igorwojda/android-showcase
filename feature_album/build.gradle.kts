plugins {
    id(GradlePluginId.androidFeature)
    id(GradlePluginId.kotlinAndroid)
    id(GradlePluginId.kotlinAndroidExtensions)
    id(GradlePluginId.ktlintGradle)
}

android {
    compileSdkVersion(ApplicationConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(ApplicationConfig.MIN_SDK_VERSION)
        targetSdkVersion(ApplicationConfig.TARGET_SDK_VERSION)
        buildToolsVersion(ApplicationConfig.BUILD_TOOLS_VERSION)

        versionCode = ApplicationConfig.VERSION_CODE
        versionName = ApplicationConfig.VERSION_NAME
        testInstrumentationRunner = ApplicationConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = ApplicationConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES
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
        unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
    }
}

androidExtensions { isExperimental = true }

dependencies {
    implementation(project(ModuleDependency.featureBase))

    addCommonDependencies()
    addTestDependencies()
}
