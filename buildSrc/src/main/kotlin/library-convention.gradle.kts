import config.JavaConfig

plugins {
    id("com.android.library")
    id("kotlin-convention")
    id("test-convention")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    val catalogs = extensions.getByType<VersionCatalogsExtension>()
    val libs = catalogs.named("libs")

    namespace = "com.igorwojda.showcase"
    compileSdk =
        libs
            .findVersion("compile-sdk")
            .get()
            .toString()
            .toInt()

    defaultConfig {
        minSdk =
            libs
                .findVersion("min-sdk")
                .get()
                .toString()
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
        sourceCompatibility = JavaConfig.JAVA_VERSION
        targetCompatibility = JavaConfig.JAVA_VERSION
    }

    kotlin {
        compilerOptions {
            jvmTarget = JavaConfig.JVM_TARGET
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

// Note: Compose dependencies are added individually in each module's build.gradle.kts
// because version catalogs are not available in precompiled script plugins
