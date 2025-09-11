plugins {
    id("showcase.android.test.library")
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
