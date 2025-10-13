plugins {
    id("com.igorwojda.showcase.convention.test.library")
}

android {
    namespace = "com.igorwojda.showcase.konsist.test"
}

dependencies {
    implementation(projects.feature.base)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.konsist)
    testImplementation(libs.viewmodel.ktx)
}
