private object LibraryVersion {
    const val KODEIN = "6.3.3"
    const val RETROFIT = "2.6.0"
    const val LOGGING_INTERCEPTOR = "4.0.0"
    const val STETHO = "1.5.0"
    const val TIMBER = "4.7.1"
    const val APP_COMPACT = "1.0.2"
    const val RECYCLER_VIEW = "1.0.0"
    const val COORDINATOR_LAYOUT = "1.0.0"
    const val MATERIAL = "1.0.0"
    const val CONSTRAINT_LAYOUT = "1.1.3"
    const val LIFECYCLE = "1.1.1"
    const val CORE_KTX = "1.0.2"
    const val FRAGMENT_KTX = "1.0.0"
    const val LIFECYCLE_VIEW_MODEL_KTX = "2.1.0-rc01"
    const val PICASSO = "2.71828"
    const val CUSTOM_FLOATING_ACTION_BUTTON = "2.1.1"
    const val K_ANDROID = "0.8.8@aar"
}

object LibraryDependency {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${CoreVersion.KOTLIN}"
    // Required by Android dynamic modules
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${CoreVersion.KOTLIN}"
    const val KODEIN = "org.kodein.di:kodein-di-generic-jvm:${LibraryVersion.KODEIN}"
    const val KODEIN_ANDROID_X = "org.kodein.di:kodein-di-framework-android-x:${LibraryVersion.KODEIN}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT}"
    const val RETROFIT_MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${LibraryVersion.RETROFIT}"
    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${LibraryVersion.LOGGING_INTERCEPTOR}"
    const val STETHO = "com.facebook.stetho:stetho:${LibraryVersion.STETHO}"
    const val STETHO_OK_HTTP = "com.facebook.stetho:stetho-okhttp3:${LibraryVersion.STETHO}"
    const val TIMBER = "com.jakewharton.timber:timber:${LibraryVersion.TIMBER}"
    const val SUPPORT_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${LibraryVersion.CONSTRAINT_LAYOUT}"
    const val APP_COMPACT = "androidx.appcompat:appcompat:${LibraryVersion.APP_COMPACT}"
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${LibraryVersion.RECYCLER_VIEW}"
    const val COORDINATOR_LAYOUT = "androidx.coordinatorlayout:coordinatorlayout:${LibraryVersion.COORDINATOR_LAYOUT}"
    const val SUPPORT_MATERIAL = "com.google.android.material:material:${LibraryVersion.MATERIAL}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${CoreVersion.COROUTINES_ANDROID}"
    const val CORE_KTX = "androidx.core:core-ktx:${LibraryVersion.CORE_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${LibraryVersion.FRAGMENT_KTX}"
    const val LIFECYCLE_EXTENSIONS = "android.arch.lifecycle:extensions:${LibraryVersion.LIFECYCLE}"
    const val LIFECYCLE_VIEW_MODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibraryVersion.LIFECYCLE_VIEW_MODEL_KTX}"
    const val PICASSO = "com.squareup.picasso:picasso:${LibraryVersion.PICASSO}"
    const val CUSTOM_FLOATING_ACTION_BUTTON =
        "com.robertlevonyan.view:CustomFloatingActionButton:${LibraryVersion.CUSTOM_FLOATING_ACTION_BUTTON}"
    const val K_ANDROID = "com.pawegio.kandroid:kandroid:${LibraryVersion.K_ANDROID}"
}
