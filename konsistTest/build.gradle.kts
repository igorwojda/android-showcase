plugins {
    id("test-library-convention-plugin")
}

android {
    namespace = "com.igorwojda.showcase.konsistTest"
}

dependencies {
    implementation(projects.feature.base)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)
    testImplementation(libs.viewmodel.ktx)
}
