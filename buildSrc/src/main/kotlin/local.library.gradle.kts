import config.JavaConfig

plugins {
    id("com.android.library")
    id("local.kotlin")
    id("local.test")
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
        sourceCompatibility = JavaConfig.javaVersion
        targetCompatibility = JavaConfig.javaVersion
    }

    kotlin {
        compilerOptions {
            jvmTarget = JavaConfig.jvmTarget
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
