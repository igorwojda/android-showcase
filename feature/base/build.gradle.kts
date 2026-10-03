plugins {
    id("com.igorwojda.showcase.convention.feature")
}

dependencies {
    implementation(platform(libs.supabase.bom))
    implementation(libs.supabase.postgrest)
}

android {
    namespace = "com.igorwojda.showcase.feature.base"
}
