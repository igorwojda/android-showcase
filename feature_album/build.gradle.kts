plugins {
    id("local.library")
    alias(libs.plugins.kotlin.symbolProcessing)
    alias(libs.plugins.safeArgs)
}

dependencies {
    implementation(projects.featureBase)

    ksp(libs.roomCompiler)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
