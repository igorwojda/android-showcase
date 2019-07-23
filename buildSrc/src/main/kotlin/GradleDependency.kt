object GradlePluginVersion {
    const val ANDROID_GRADLE = "3.4.1"
    const val KTLINT_GRADLE = "8.1.0"
    const val DETEKT = "1.0.0-RC16"
    const val GRADLE_VERSION_PLUGIN = "0.21.0"
}

object GradlePluginId {
    const val DETEKT = "io.gitlab.arturbosch.detekt"
    const val KTLINT_GRADLE = "org.jlleitschuh.gradle.ktlint"
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_DYNAMIC_FEATURE = "com.android.dynamic-feature"
    const val ANDROID_LIBRARY = "com.android.library"
    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_ANDROID_EXTENSIONS = "kotlin-android-extensions"
    const val GRADLE_VERSION_PLUGIN = "com.github.ben-manes.versions"
}

object GradleDependency {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${CoreVersion.KOTLIN}"
    const val ANDROID_GRADLE = "com.android.tools.build:gradle:${GradlePluginVersion.ANDROID_GRADLE}"
    const val KTLINT_GRADLE = "org.jlleitschuh.gradle:ktlint-gradle:${GradlePluginVersion.KTLINT_GRADLE}"
}
