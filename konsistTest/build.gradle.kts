plugins {
    id("local.library")
}

android {
    namespace = "com.igorwojda.showcase.konsistTest"
}

dependencies {
    implementation(projects.feature.base)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)
}
