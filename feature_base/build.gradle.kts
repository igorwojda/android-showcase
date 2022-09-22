plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.symbolProcessing)
    alias(libs.plugins.safeArgs)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.testLogger)
    alias(libs.plugins.junit5Android)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        targetSdk = 33

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
    // See Dependency management section in the README.md
    // https://github.com/igorwojda/android-showcase#dependency-management
    api(libs.kotlin)
    api(libs.koin)
    api(libs.bundles.retrofit)
    api(libs.playCore)
    api(libs.coreKtx)
    api(libs.fragmentKtx)
    api(libs.bundles.navigation)
    api(libs.bundles.lifecycle)
    api(libs.bundles.room)
    api(libs.viewBindingPropertyDelegate)
    api(libs.timber)
    api(libs.coil)
    api(libs.constraintLayout)
    api(libs.coordinatorLayout)
    api(libs.appCompat)
    api(libs.recyclerView)
    api(libs.material)
    api(libs.coroutines)
    api(libs.lottie)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
