import ext.libs
import gradle.kotlin.dsl.accessors._b8562b6270e8bcefd6bb0323b2a2c4b6.debugImplementation
import gradle.kotlin.dsl.accessors._b8562b6270e8bcefd6bb0323b2a2c4b6.implementation

plugins {
    id("com.android.application")
    id("kotlin-convention")
    id("spotless-convention")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.compose")
}

/*
The "lib" is available, because implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location)) dependency
makes generated type-safe version catalogs accessors accessible from precompiled script plugins
See https://github.com/gradle/gradle/issues/15383
*/
dependencies {
    implementation(libs.kotlin)
    implementation(libs.core.ktx)
    implementation(libs.timber)
    implementation(libs.app.compat)
    implementation(libs.coroutines)
    implementation(libs.material)
    implementation(libs.compose.material)

    // Compose dependencies
    implementation(platform(libs.compose.bom))
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.lifecycle)
}
