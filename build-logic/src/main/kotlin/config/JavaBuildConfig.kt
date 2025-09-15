package config

import org.gradle.api.JavaVersion

object JavaBuildConfig {
    val JAVA_VERSION: JavaVersion = JavaVersion.VERSION_17
    const val JVM_TOOLCHAIN_VERSION: Int = 17
}
