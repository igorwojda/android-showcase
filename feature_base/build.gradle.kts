plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    // Gradle 7 introduces version catalogs - a new way for sharing dependency versions across projects.
    // Dependencies are defined in gradle.settings.kts file.
    api(libs.bundles.kotlin)
    api(libs.bundles.koin)
    api(libs.bundles.retrofit)
    api(libs.bundles.okhttp)
    api(libs.play.core)
    api(libs.bundles.ktx)
    api(libs.bundles.navigation)
    api(libs.bundles.lifecycle)
    api(libs.bundles.room)
    api(libs.viewbindingpropertydelegate)
    api(libs.timber)
    api(libs.coil)
    api(libs.constraintLayout)
    api(libs.coordinatorLayout)
    api(libs.appcompat)
    api(libs.recyclerview)
    api(libs.material)
    api(libs.coroutines)
    api(libs.lottie)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junit.jupiter.engine)
}
