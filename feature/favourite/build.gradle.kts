plugins {
    id("feature-convention")
}

android {
    namespace = "com.igorwojda.showcase.feature.favourite"
}

dependencies {
    api(projects.feature.base)

    testImplementation(projects.library.testUtils)
}
