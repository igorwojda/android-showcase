
import com.android.build.api.dsl.ApplicationDefaultConfig
import config.JavaConfig
import java.util.Locale

plugins {
    id("app-convention")
}

android {
    val catalogs = extensions.getByType<VersionCatalogsExtension>()
    val libs = catalogs.named("libs")

    namespace = "com.igorwojda.showcase.app"

    compileSdk =
        libs
            .findVersion("compile-sdk")
            .get()
            .toString()
            .toInt()

    defaultConfig {
        applicationId = "com.igorwojda.showcase"

        minSdk =
            libs
                .findVersion("min-sdk")
                .get()
                .toString()
                .toInt()

        targetSdk =
            libs
                .findVersion("target-sdk")
                .get()
                .toString()
                .toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        versionCode = 1
        versionName = "0.0.1" // SemVer (Major.Minor.Patch)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true

        vectorDrawables {
            useSupportLibrary = true
        }

        lint {
            baseline = file("android-lint-baseline.xml")
        }

        buildConfigFieldFromGradleProperty("apiBaseUrl")
        buildConfigFieldFromGradleProperty("apiToken")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaConfig.javaVersion
        targetCompatibility = JavaConfig.javaVersion
    }

    kotlin {
        compilerOptions {
            jvmTarget = JavaConfig.jvmTarget
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    // "projects." Syntax utilizes Gradle TYPESAFE_PROJECT_ACCESSORS feature
    implementation(projects.feature.album)
    implementation(projects.feature.profile)
    implementation(projects.feature.favourite)

    implementation(platform(libs.compose.bom))
    implementation(libs.navigation.compose)
    
    // Debug dependencies for Compose Previews
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
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
