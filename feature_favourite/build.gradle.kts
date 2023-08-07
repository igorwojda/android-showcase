plugins {
    id("local.library")
}

android {
    namespace = "com.igorwojda.showcase.favourite"
}

dependencies {
    api(projects.featureBase)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
