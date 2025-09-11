plugins {
    id("feature-convention")
}

android {
    namespace = "com.igorwojda.showcase.feature.base"
}

dependencies {
    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junit.jupiter.engine)
}
