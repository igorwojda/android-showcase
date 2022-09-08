import com.android.build.api.dsl.ApplicationDefaultConfig

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.igorwojda.showcase"

        versionCode = 1
        versionName = "1.0"
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigFieldFromGradleProperty("apiBaseUrl")
        buildConfigFieldFromGradleProperty("apiToken")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        testOptions {
            unitTests.isReturnDefaultValues = true
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    // Syntax utilizes Gradle TYPESAFE_PROJECT_ACCESSORS feature
    implementation(projects.featureAlbum)
    implementation(projects.featureProfile)
    implementation(projects.featureFavourite)
}

/*
Takes value from Gradle project property and sets it as Android build config property eg.
apiToken variable present in the settings.gradle file will be accessible as BuildConfig.GRADLE_API_TOKEN in the app.
 */
fun ApplicationDefaultConfig.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

    val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".toUpperCase()
    buildConfigField("String", androidResourceName, propertyValue)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }
