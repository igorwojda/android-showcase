import config.JavaBuildConfig

plugins {
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
    jvmToolchain(JavaBuildConfig.JVM_TOOLCHAIN_VERSION)
}
