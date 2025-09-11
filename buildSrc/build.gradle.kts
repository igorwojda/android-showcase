plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(plugin(libs.plugins.kotlin.android))
    implementation(plugin(libs.plugins.kotlin.serialization))
    implementation(plugin(libs.plugins.kotlin.symbol.processing))
    implementation(plugin(libs.plugins.android.application))
    implementation(plugin(libs.plugins.android.library))
    implementation(plugin(libs.plugins.spotless))
    implementation(plugin(libs.plugins.test.logger))
    implementation(plugin(libs.plugins.detekt))
    implementation(plugin(libs.plugins.junit5.android))
    implementation(plugin(libs.plugins.compose))
}

kotlin {
    val catalogs = extensions.getByType<VersionCatalogsExtension>()
    val libs = catalogs.named("libs")

    val javaVersion = libs
        .findVersion("java")
        .get()
        .toString()
        .toInt()

    // Version value should match JavaConfig.jvmToolchainVersion
    jvmToolchain(javaVersion)
}

fun plugin(plugin: Provider<PluginDependency>) = plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
