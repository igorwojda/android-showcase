import com.android.build.gradle.internal.dsl.BaseFlavor

plugins {
    id(GradlePluginId.ANDROID_APPLICATION)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    id(GradlePluginId.KTLINT_GRADLE)
}

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = AndroidConfig.ID
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables.useSupportLibrary = AndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES

        stringResValue("apiBaseUrl")
        stringResValue("apiToken")
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

    dynamicFeatures = mutableSetOf(ModuleDependency.FEATURE_ALBUM)
}

androidExtensions { isExperimental = true }

dependencies {
    // api means that all features modules that depend on app will have access to dependencies defined in LIBRARY_BASE
    api(project(ModuleDependency.LIBRARY_BASE))

    implementation(LibraryDependency.LOGGING_INTERCEPTOR)
    implementation(LibraryDependency.PLAY_CORE)

    addCommonDependencies()
    addTestDependencies()
}

fun BaseFlavor.stringResValue(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "$gradlePropertyName is null" }

    val androidResourceName = "build_param_${gradlePropertyName.toSnakeCase()}"
    resValue("string", androidResourceName, propertyValue)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }
