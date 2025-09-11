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

    /*
    Makes generated type-safe version catalogs accessors accessible from precompiled script plugins
    e.g. add("implementation", libs.koin)
    https://github.com/gradle/gradle/issues/15383
     */
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

kotlin {
    val catalogs = extensions.getByType<VersionCatalogsExtension>()
    val libs = catalogs.named("libs")

    val javaVersion =
        libs
            .findVersion("java")
            .get()
            .toString()
            .toInt()

    jvmToolchain(javaVersion)
}

fun plugin(plugin: Provider<PluginDependency>) = plugin.map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }

// region Generate JavaBuildConfig.kt
tasks.register("generateJavaBuildConfig") {
    val outputFile =
        layout.buildDirectory
            .file("generated/sources/javaBuildConfig/kotlin/config/JavaBuildConfig.kt")
            .get()
            .asFile
    outputs.file(outputFile)

    doLast {
        outputFile.parentFile.mkdirs()
        outputFile.writeText(
            """
            package config

            import org.gradle.api.JavaVersion
            import org.jetbrains.kotlin.gradle.dsl.JvmTarget

            object JavaBuildConfig {
                val JAVA_VERSION: JavaVersion = JavaVersion.VERSION_$tomlJavaVersion
                val JVM_TARGET: JvmTarget = JvmTarget.JVM_$tomlJavaVersion
                const val JVM_TOOLCHAIN_VERSION: Int = $tomlJavaVersion
            }
            """.trimIndent(),
        )
        println("✅ Generated JavaBuildConfig.kt with JAVA_VERSION=$tomlJavaVersion")
    }
}

sourceSets.main {
    java.srcDir(layout.buildDirectory.dir("generated/sources/javaBuildConfig/kotlin"))
}

tasks.named("compileKotlin") {
    dependsOn("generateJavaBuildConfig")
}

/**
 * Reads the Java version from the `gradle/libs.versions.toml` file.
 * (VersionCatalogsExtension is not available at this stage).
 */
val tomlJavaVersion by lazy {
    val mainProjectRootDir =
        gradle.parent?.rootProject?.rootDir
            ?: throw GradleException("❌ Could not resolve main project rootDir")

    val tomlFile = mainProjectRootDir.resolve("gradle/libs.versions.toml")

    tomlFile
        .readLines()
        .firstOrNull { it.trim().startsWith("java") }
        ?.substringAfter("=")
        ?.replace("\"", "")
        ?.trim()
        ?: error("❌ Could not find 'java' version in libs.versions.toml file")
}
// endregion
