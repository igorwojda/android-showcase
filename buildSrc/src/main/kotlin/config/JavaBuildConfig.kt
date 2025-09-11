package config

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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
object JavaBuildConfig {
    // // Version value should match version in buildSrc/build.gradle.kts
    private const val VERSION = 17

    val JAVA_VERSION: JavaVersion = JavaVersion.VERSION_17
    val JVM_TARGET: JvmTarget = JvmTarget.JVM_17
    const val JVM_TOOLCHAIN_VERSION: Int = VERSION
}
