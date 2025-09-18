plugins {
    id("com.igorwojda.showcase.convention.test.library")
}

android {
    namespace = "com.igorwojda.showcase.konsistTest"
}

dependencies {
    implementation(projects.feature.base)

    testImplementation(projects.library.testUtils)
    testImplementation(libs.bundles.test)
    testImplementation(libs.viewmodel.ktx)
}

// Enable tests only for 'konsistTest:test' tasks but disable for others like 'testDebugUnitTest'
tasks.withType<Test>().configureEach {
    val taskPath = path

    enabled = taskPath.contains("konsistTest:test") // Enable if it's the konsistTest module's test task
}
