import config.JavaBuildConfig
import ext.libs
import ext.versions
import gradle.kotlin.dsl.accessors._b8562b6270e8bcefd6bb0323b2a2c4b6.debugImplementation
import gradle.kotlin.dsl.accessors._b8562b6270e8bcefd6bb0323b2a2c4b6.implementation

plugins {
    id("com.android.application")
    id("kotlin-convention")
    id("spotless-convention")
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
        applicationId = "com.igorwojda.showcase"

        minSdk = versions
            .min
            .sdk
            .get()
            .toInt()

        targetSdk = versions
            .target
            .sdk
            .get()
            .toInt()

        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        lint {
            baseline = file("android-lint-baseline.xml")
        }
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

    packaging {
        resources.excludes +=
            setOf(
                "META-INF/AL2.0",
                "META-INF/licenses/**",
                "**/attach_hotspot_windows.dll",
                "META-INF/LGPL2.1",
            )
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation(libs.kotlin)
    implementation(libs.core.ktx)
    implementation(libs.timber)
    implementation(libs.app.compat)
    implementation(libs.coroutines)
    implementation(libs.material)
    implementation(libs.compose.material)

    // Compose dependencies
    implementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.compose)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.lifecycle)
}
