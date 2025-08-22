plugins {
    id("local.library")
}

android {
    namespace = "com.igorwojda.showcase.konsistTest"
}

dependencies {
    implementation(projects.feature.base)

    testImplementation(projects.lib.testUtils)
    testImplementation(libs.bundles.test)
}
