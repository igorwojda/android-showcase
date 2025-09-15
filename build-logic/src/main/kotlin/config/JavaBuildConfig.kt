package config

import org.gradle.api.JavaVersion
import java.io.File

object JavaBuildConfig {
    /**
     * Reads the Java version from the `gradle/libs.versions.toml` file.
     * (VersionCatalogsExtension is not available at this stage).
     */
    private val tomlJavaVersion by lazy {
        File(System.getProperty("user.dir"))
            .resolve("gradle/libs.versions.toml")
            .readLines()
            .firstOrNull { it.trim().startsWith("java") }
            ?.substringAfter("=")
            ?.trim('"', ' ')
            ?: error("❌ Could not find 'java' version in libs.versions.toml file")
    }

    /*
    Configure the build-logic config to target JDK from version catalog
    This matches the JDK used to build the project.
     */
    val JAVA_VERSION: JavaVersion = JavaVersion.toVersion(tomlJavaVersion)
    val JVM_TOOLCHAIN_VERSION: Int = tomlJavaVersion.toInt()
}
