package com.igorwojda.showcase.buildlogic.ext

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.LibraryDefaultConfig
import org.gradle.api.Project
import java.util.Locale

/**
 * Takes value from Gradle project property and sets it as Android build config property.
 * Example: apiToken variable present in the settings.gradle file will be accessible
 * as BuildConfig.GRADLE_API_TOKEN in the app.
 */
fun ApplicationDefaultConfig.buildConfigFieldFromGradleProperty(
    project: Project,
    gradlePropertyName: String,
) {
    val (androidResourceName, propertyValue) = extractBuildConfigField(project, gradlePropertyName)
    buildConfigField("String", androidResourceName, propertyValue)
}

/**
 * Takes value from Gradle project property and sets it as Android build config property.
 * Example: apiToken variable present in the settings.gradle file will be accessible
 * as BuildConfig.GRADLE_API_TOKEN in the library.
 */
fun LibraryDefaultConfig.buildConfigFieldFromGradleProperty(
    project: Project,
    gradlePropertyName: String,
) {
    val (androidResourceName, propertyValue) = extractBuildConfigField(project, gradlePropertyName)
    buildConfigField("String", androidResourceName, propertyValue)
}

private fun extractBuildConfigField(
    project: Project,
    gradlePropertyName: String,
): Pair<String, String> {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

    val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".uppercase(Locale.getDefault())
    return androidResourceName to propertyValue
}

private fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.lowercase(Locale.getDefault()) }
