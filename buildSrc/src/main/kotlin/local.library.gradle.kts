import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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
            .findVersion("compileSdk")
            .get()
            .toString()
            .toInt()

    defaultConfig {
        minSdk =
            libs
                .findVersion("minSdk")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    @Suppress("UnstableApiUsage")
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
