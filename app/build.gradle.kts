import com.android.build.gradle.internal.dsl.BaseFlavor

plugins {
    id(GradlePluginId.androidApplication)
    id(GradlePluginId.kotlinAndroid)
    id(GradlePluginId.kotlinAndroidExtensions)
    id(GradlePluginId.ktlintGradle)
}

android {
    compileSdkVersion(ApplicationConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = ApplicationConfig.ID
        minSdkVersion(ApplicationConfig.MIN_SDK_VERSION)
        targetSdkVersion(ApplicationConfig.TARGET_SDK_VERSION)
        buildToolsVersion(ApplicationConfig.BUILD_TOOLS_VERSION)

        versionCode = ApplicationConfig.VERSION_CODE
        versionName = ApplicationConfig.VERSION_NAME
        testInstrumentationRunner = ApplicationConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = ApplicationConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES

        stringResValue("apiBaseUrl", "api_base_url")
        stringResValue("apiToken", "api_token")
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
            unitTests.isReturnDefaultValues = TestOptions.IS_RETURN_DEFAULT_VALUES
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

fun BaseFlavor.stringResValue(gradlePropertyName: String, androidResourceName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "$gradlePropertyName is null"}
    resValue("string", androidResourceName, propertyValue)
}
