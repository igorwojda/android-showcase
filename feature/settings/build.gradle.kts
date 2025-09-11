plugins {
    id("feature-convention")
}

android {
    namespace = "com.igorwojda.showcase.feature.profle"
}

dependencies {
    implementation(projects.feature.base)

    testRuntimeOnly(libs.junit.jupiter.engine)
}
