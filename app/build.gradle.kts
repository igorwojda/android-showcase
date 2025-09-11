import com.android.build.api.dsl.ApplicationDefaultConfig
import java.util.Locale

plugins {
    id("app-convention-plugin")
}

android {
    namespace = "com.igorwojda.showcase.app"

    defaultConfig {
        applicationId = "com.igorwojda.showcase"

        versionCode = 1
        versionName = "0.0.1" // SemVer (Major.Minor.Patch)

        buildConfigFieldFromGradleProperty("apiBaseUrl")
        buildConfigFieldFromGradleProperty("apiToken")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }
}

dependencies {
    // "projects." Syntax utilizes Gradle TYPESAFE_PROJECT_ACCESSORS feature
    implementation(projects.feature.base)
    implementation(projects.feature.album)
    implementation(projects.feature.settings)
    implementation(projects.feature.favourite)
}

/*
Takes value from Gradle project property and sets it as Android build config property eg.
apiToken variable present in the settings.gradle file will be accessible as BuildConfig.GRADLE_API_TOKEN in the app.
 */
fun ApplicationDefaultConfig.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

    val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".uppercase(Locale.getDefault())
    buildConfigField("String", androidResourceName, propertyValue)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.lowercase(Locale.getDefault()) }
