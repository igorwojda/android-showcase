plugins {
    id("local.library")
    alias(libs.plugins.kotlin.symbolProcessing)
    alias(libs.plugins.safeArgs)
}

android {
    namespace = "com.igorwojda.showcase.feature.album"
}

dependencies {
    implementation(projects.featureBase)

    ksp(libs.roomCompiler)

    testImplementation(projects.libraryTestUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
