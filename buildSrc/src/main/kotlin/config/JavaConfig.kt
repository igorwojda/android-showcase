package config

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

/**
 * Centralized place for Java/Kotlin version configuration
 *
 * ```kotlin
 * compileOptions {
 *     sourceCompatibility = JavaConfig.javaVersion
 *     targetCompatibility = JavaConfig.javaVersion
 * }
 *
 * kotlin {
 *     compilerOptions {
 *         jvmTarget = JavaConfig.jvmTarget
 *     }
 * }
 * ```
 */
object JavaConfig {
    private const val VERSION_STRING = "17"

    val javaVersion: JavaVersion = JavaVersion.VERSION_17
    val jvmTarget: JvmTarget = JvmTarget.JVM_17
    val versionString: String = VERSION_STRING
}
