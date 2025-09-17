plugins {
    id("com.igorwojda.showcase.convention.feature")
}

android {
    namespace = "com.igorwojda.showcase.feature.settings"
}

dependencies {
    implementation(libs.aboutlibraries.compose)
}
