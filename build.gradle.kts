plugins {
    alias(libs.plugins.safeArgs) apply false
    id("local.detekt")
    id("local.spotless")
}
