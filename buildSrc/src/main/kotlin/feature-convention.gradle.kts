
import config.JavaBuildConfig
import ext.libs
import ext.versions

plugins {
    id("com.android.library")
    id("kotlin-convention")
    id("test-convention")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    compileSdk =
        versions
            .compile
            .sdk
            .get()
            .toInt()

    defaultConfig {
        minSdk =
            versions
                .min
                .sdk
                .get()
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

dependencies {
    implementation(libs.kotlin)
    implementation(libs.core.ktx)
    implementation(libs.timber)
    implementation(libs.coroutines)
    implementation(libs.material)
    implementation(libs.compose.material)

    // Compose dependencies
    implementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.lifecycle)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)

    // Test dependencies
    testImplementation(project(":library:testUtils"))

    testImplementation(libs.bundles.test)
    testRuntimeOnly(libs.junit.jupiter.engine)
}
