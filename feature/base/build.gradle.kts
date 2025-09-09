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
    api(libs.core.ktx)
    api(libs.timber)
    api(libs.app.compat)
    api(libs.coroutines)
    api(libs.material)
    api(libs.compose.material)

    // Koin
    api(platform(libs.koin.bom))
    api(libs.bundles.koin)

    api(libs.bundles.retrofit)
    api(libs.bundles.navigation)
    api(libs.bundles.lifecycle)
    api(libs.bundles.room)

    implementation(platform(libs.compose.bom))
    api(libs.bundles.compose)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junit.jupiter.engine)
}
