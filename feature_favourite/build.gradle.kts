plugins {
    id("local.library")
    alias(libs.plugins.kotlin.symbolProcessing)
    alias(libs.plugins.safeArgs)
}

android {
    namespace = "com.igorwojda.showcase.feature.favourite"
}

dependencies {
    api(projects.featureBase)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
