plugins {
    id("feature-convention")
}

android {
    namespace = "com.igorwojda.showcase.feature.album"
}

dependencies {
    implementation(projects.feature.base)
}
