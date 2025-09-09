import config.JavaConfig

plugins {
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
    jvmToolchain(JavaConfig.JVM_TOOLCHAIN_VERSION)
}
