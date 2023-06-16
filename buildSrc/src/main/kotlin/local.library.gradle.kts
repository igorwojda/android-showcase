plugins {
    id("com.android.library")
    id("local.kotlin")
    id("local.test")
}

android {
    namespace = "com.igorwojda.showcase"

    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    @Suppress("UnstableApiUsage")
    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"//TODO: libs.versions.kotlinCompilerExtensionVersion
        // .get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    packaging {
        resources.excludes += setOf(
            "META-INF/AL2.0",
            "META-INF/licenses/**",
            "**/attach_hotspot_windows.dll",
            "META-INF/LGPL2.1",
        )
    }
}
