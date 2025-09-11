import config.JavaBuildConfig
import ext.versions

plugins {
    id("com.android.library")
    id("kotlin-convention")
    id("test-convention")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    compileSdk = versions
        .compile
        .sdk
        .get()
        .toInt()

    defaultConfig {
        minSdk = versions
            .min
            .sdk
            .get()
            .toInt()
            .toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaBuildConfig.JAVA_VERSION
        targetCompatibility = JavaBuildConfig.JAVA_VERSION
    }

    kotlin {
        compilerOptions {
            jvmTarget = JavaBuildConfig.JVM_TARGET
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    packaging {
        resources.excludes +=
            setOf(
                "META-INF/AL2.0",
                "META-INF/licenses/**",
                "**/attach_hotspot_windows.dll",
                "META-INF/LGPL2.1",
            )
    }
}
