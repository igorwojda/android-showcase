plugins {
    id("local.library")
}

android {
    namespace = "com.igorwojda.showcase.feature.base"
}

dependencies {
    // See Dependency management section in the README.md
    // https://github.com/igorwojda/android-showcase#dependency-management
    api(libs.kotlin)
    api(libs.coreKtx)
    api(libs.timber)
    api(libs.appCompat)
    api(libs.coroutines)
    api(libs.material)
    api(libs.composeMaterial)
    api(libs.accompanistFlowLayout)

    // Koin
    api(platform(libs.koin.bom))
    api(libs.bundles.koin)

    api(libs.bundles.retrofit)
    api(libs.bundles.navigation)
    api(libs.bundles.lifecycle)
    api(libs.bundles.room)
    api(libs.bundles.compose)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
