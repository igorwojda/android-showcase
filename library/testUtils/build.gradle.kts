plugins {
    id("showcase.android.library")
}

android {
    namespace = "com.igorwojda.showcase.library.testutils"
}

dependencies {
    // implementation configuration is used here (instead of testImplementation) because this module is added as
    // testImplementation dependency inside other modules. Using implementation allows to write tests for test utilities.
    implementation(libs.kotlin)
    implementation(libs.bundles.test)
    implementation(libs.bundles.compose)

    runtimeOnly(libs.junit.jupiter.engine)
}
