plugins {
    id("local.library")
}

android {
    namespace = "com.igorwojda.showcase.feature.profle"
}

dependencies {
    implementation(projects.feature.base)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
