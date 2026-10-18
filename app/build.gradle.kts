import com.igorwojda.showcase.buildlogic.ext.buildConfigFieldFromGradleProperty

plugins {
    id("com.igorwojda.showcase.convention.application")
}

android {
    namespace = "com.igorwojda.showcase.app"

    defaultConfig {
        applicationId = "com.igorwojda.showcase"

        versionCode = 1
        versionName = "0.0.1" // SemVer (Major.Minor.Patch)

        buildConfigFieldFromGradleProperty(project, "apiBaseUrl")
        buildConfigFieldFromGradleProperty(project, "apiToken")
        buildConfigField("String", "GRADLE_SUPABASE_URL", "\"${providers.gradleProperty("supabaseUrl").orElse("").get()}\"")
        buildConfigField("String", "GRADLE_SUPABASE_ANON_KEY", "\"${providers.gradleProperty("supabaseAnonKey").orElse("").get()}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(platform(libs.supabase.bom))
    implementation(libs.bundles.supabase)
    implementation(libs.ktor.client.okhttp)
    // "projects." Syntax utilizes Gradle TYPESAFE_PROJECT_ACCESSORS feature
    implementation(projects.feature.base)
    implementation(projects.feature.album)
    implementation(projects.feature.settings)
    implementation(projects.feature.favourite)
}
