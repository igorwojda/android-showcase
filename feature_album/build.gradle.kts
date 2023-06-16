plugins {
    id("local.library")
    alias(libs.plugins.kotlin.symbolProcessing)
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
