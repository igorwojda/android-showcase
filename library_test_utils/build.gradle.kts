plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdk = 33

    packagingOptions {
        resources.excludes += setOf(
            "META-INF/AL2.0",
            "META-INF/licenses/**",
            "**/attach_hotspot_windows.dll",
            "META-INF/LGPL2.1",
        )
    }
}

dependencies {
    // implementation configuration is used here (instead of testImplementation) because this module is added as
    // testImplementation dependency inside other modules. Using implementation allows to write tests for test utilities.
    implementation(libs.kotlin)
    implementation(libs.bundles.test)

    runtimeOnly(libs.junitJupiterEngine)
}
