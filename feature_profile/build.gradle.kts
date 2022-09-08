plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdk = 33

    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(project(":feature_base"))
    
    testImplementation(project(":library_test_utils"))
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junit.jupiter.engine)
}
