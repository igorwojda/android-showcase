plugins {
    id("local.feature")
    alias(libs.plugins.kotlin.symbolProcessing)
    alias(libs.plugins.safeArgs)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.featureBase)

    ksp(libs.roomCompiler)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
