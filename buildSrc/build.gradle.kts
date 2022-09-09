plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.kotlinGradlePlugin)
    implementation(libs.androidGradlePlugin)
    implementation(libs.kotlinSerializationPlugin)
    implementation(libs.kotlinSymbolProcessingPlugin)
    implementation(libs.safeArgsPlugin)
    implementation(libs.junit5AndroidPlugin)
    implementation(libs.detektPlugin)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}
