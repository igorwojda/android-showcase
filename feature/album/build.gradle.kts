plugins {
    id("local.library")
}

android {
    namespace = "com.igorwojda.showcase.feature.album"
}

dependencies {
    implementation(projects.feature.base)

    ksp(libs.roomCompiler)

    testImplementation(projects.lib.testUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junitJupiterEngine)
}
