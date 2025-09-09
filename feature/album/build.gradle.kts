plugins {
    id("com.android.library")
    id("library-convention")
}

android {
    namespace = "com.igorwojda.showcase.feature.album"
}

dependencies {
    implementation(projects.feature.base)

    ksp(libs.room.compiler)

    // Compose dependencies
    implementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junit.jupiter.engine)
}
