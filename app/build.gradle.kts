import com.android.build.api.dsl.ApplicationDefaultConfig
import kotlin.reflect.full.memberProperties

plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint")
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

        buildConfigFieldFromGradleProperty("apiBaseUrl")
        buildConfigFieldFromGradleProperty("apiToken")

        buildConfigField("FEATURE_MODULE_NAMES", getFeatureNames())
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        testOptions {
            unitTests.isReturnDefaultValues = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Gradle 7 introduces version catalogs - a new way for sharing dependency versions across projects.
    // Dependencies are defined in gradle.settings.kts file.
    api(libs.bundles.kotlin)
    api(libs.bundles.koin)
    api(libs.bundles.retrofit)
    api(libs.bundles.okhttp)
    api(libs.play.core)
    api(libs.bundles.ktx)
    api(libs.bundles.navigation)
    api(libs.bundles.lifecycle)
    api(libs.bundles.room)
    api(libs.timber)
    api(libs.coil)
    api(libs.constraintLayout)
    api(libs.coordinatorLayout)
    api(libs.appcompat)
    api(libs.recyclerview)
    api(libs.material)
    api(libs.coroutines)
    api(libs.lottie)

    ksp(libs.room.compiler)

    testImplementation(project(":library_test_utils"))
    testImplementation(libs.bundles.test)

    testRuntimeOnly(libs.junit.jupiter.engine)
}

/*
Takes value from Gradle project property and sets it as build config property
 */
fun com.android.build.api.dsl.ApplicationDefaultConfig.buildConfigFieldFromGradleProperty(gradlePropertyName: String) {
    val propertyValue = project.properties[gradlePropertyName] as? String
    checkNotNull(propertyValue) { "Gradle property $gradlePropertyName is null" }

    val androidResourceName = "GRADLE_${gradlePropertyName.toSnakeCase()}".toUpperCase()
    buildConfigField("String", androidResourceName, propertyValue)
}

fun String.toSnakeCase() = this.split(Regex("(?=[A-Z])")).joinToString("_") { it.toLowerCase() }

/*
Return names of the features
 */
fun getFeatureNames() =
    getFeatureModules()
        .map { it.replace(":feature_", "") }
        .toTypedArray()

/*
 Return list of feature modules in the project
 */
fun getFeatureModules(): Set<String> {
    val featurePrefix = ":feature_"

    return getAllModules()
        .filter { it.startsWith(featurePrefix) }
        .toSet()
}

// False positive" function can be private"
// See: https://youtrack.jetbrains.com/issue/KT-33610
/*
Return list of all modules in the project
 */
fun getAllModules() = ModuleDependency::class.memberProperties
    .filter { it.isConst }
    .map { it.getter.call().toString() }
    .toSet()

/*
Adds a new field to the generated BuildConfig class
 */
fun ApplicationDefaultConfig.buildConfigField(name: String, value: Array<String>) {
    // Create String that holds Java String Array code
    val strValue = value.joinToString(prefix = "{", separator = ",", postfix = "}", transform = { "\"$it\"" })
    buildConfigField("String[]", name, strValue)
}
