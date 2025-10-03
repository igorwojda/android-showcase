plugins {
    id("com.igorwojda.showcase.convention.test.library")
}

android {
    namespace = "com.igorwojda.showcase.konsist.test"
}

dependencies {
    implementation(projects.feature.base)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)
    testImplementation(libs.viewmodel.ktx)
}
