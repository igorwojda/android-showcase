plugins {
    id("local.library")
}

android {
    namespace = "com.igorwojda.showcase.feature.favourite"
}

dependencies {
    api(projects.feature.base)

    testImplementation(projects.lib.testUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
