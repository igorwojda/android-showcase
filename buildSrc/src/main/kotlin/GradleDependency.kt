import org.gradle.plugin.use.PluginDependenciesSpec

object GradlePluginVersion {
    const val androidGradle = "3.2.1"

    const val ktlintGradle = "6.2.0"
    const val detekt = "1.0.0.RC9.2"
}

object GradlePluginId {
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val ktlintGradle = "org.jlleitschuh.gradle.ktlint"
    const val androidApplication = "com.android.application"
    const val androidFeature = "com.android.feature"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
}

object GradleDependency {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${CoreVersion.kotlin}"
    const val androidGradle = "com.android.tools.build:gradle:${GradlePluginVersion.androidGradle}"
    const val ktlintGradle = "gradle.plugin.org.jlleitschuh.gradle:ktlint-gradle:${GradlePluginVersion.ktlintGradle}"
}
