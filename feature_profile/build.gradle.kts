plugins {
    id("local.library")
}

android {
    namespace = "com.igorwojda.showcase.profle"
}

dependencies {
    implementation(projects.featureBase)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
