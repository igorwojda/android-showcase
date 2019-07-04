plugins {
    id(GradlePluginId.androidApplication)
    id(GradlePluginId.kotlinAndroid)
    id(GradlePluginId.kotlinAndroidExtensions)
    id(GradlePluginId.ktlintGradle)
}

android {
    compileSdkVersion(ApplicationConfig.compileSdkVersion)

    defaultConfig {
        applicationId = ApplicationConfig.id
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

        testOptions {
            unitTests.isReturnDefaultValues = true
        }

        testOptions {
            unitTests.isReturnDefaultValues = TestOptions.isReturnDefaultValues
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}

androidExtensions { isExperimental = true }

dependencies {
    // module
    implementation(project(ModuleDependency.featureBase))
    implementation(project(ModuleDependency.featureAlbum))

    addCommonDependencies()
    addTestDependencies()
}
