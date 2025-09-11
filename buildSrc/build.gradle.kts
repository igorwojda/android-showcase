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

val javaVersion: String = checkNotNull(providers.gradleProperty("javaVersion").orNull) {
    "Set javaVersion property in gradle.properties"
}

// region Generate JavaBuildConfig.kt
tasks.register("generateJavaBuildConfig") {
    val outputFile = layout.buildDirectory.file("generated/sources/javaBuildConfig/kotlin/config/JavaBuildConfig.kt").get().asFile
    outputs.file(outputFile)

    doLast {
        outputFile.parentFile.mkdirs()
        outputFile.writeText(
            """
            /**
             * Centralized place for Java/Kotlin version configuration
             *
             * ```kotlin
             * compileOptions {
             *     sourceCompatibility = JavaBuildConfig.JAVA_VERSION
             *     targetCompatibility = JavaBuildConfig.JAVA_VERSION
             * }
             *
             * kotlin {
             *     compilerOptions {
             *         jvmTarget = JavaBuildConfig.jvmTarget
             *     }
             *     jvmToolchain(JavaBuildConfig.jvmToolchainVersion)
             * }
             * ```
             */    
            package config

            import org.gradle.api.JavaVersion
            import org.jetbrains.kotlin.gradle.dsl.JvmTarget

            object JavaBuildConfig {
                val JAVA_VERSION: JavaVersion = JavaVersion.VERSION_${javaVersion}
                val JVM_TARGET: JvmTarget = JvmTarget.JVM_${javaVersion}
                const val JVM_TOOLCHAIN_VERSION: Int = $javaVersion
            }
            """.trimIndent()
        )
        println("✅ Generated JavaBuildConfig.kt with JAVA_VERSION=$javaVersion")
    }
}

sourceSets.main {
    java.srcDir(layout.buildDirectory.dir("generated/sources/javaBuildConfig/kotlin"))
}

tasks.named("compileKotlin") {
    dependsOn("generateJavaBuildConfig")
}
// endregion
