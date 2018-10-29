private object LibraryVersion {
    const val kodein = "5.2.0"

    //jvm
    const val retrofit = "2.4.0"
    const val gson = "2.8.3"

    //android
    const val supportLibraries = "28.0.0"
    const val constraintLayout = "1.1.3"

    //jvmTest
    const val junit = "4.12"
    const val kluent = "1.36"

    //androidTest
    const val testRunner = "1.0.2"
    const val espressoCore = "3.0.2"
}

object LibraryDependency {
    //core
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${CoreVersion.kotlin}"
    const val kodein = "org.kodein.di:kodein-di-generic-jvm:${LibraryVersion.kodein}"
    const val kodeinAndroid = "org.kodein.di:kodein-di-framework-android-support:${LibraryVersion.kodein}"

    //jvm
    const val retrofit = "com.squareup.retrofit2:retrofit:${LibraryVersion.retrofit}"
    const val gson = "com.google.code.gson:gson:${LibraryVersion.gson}"

    //android
    const val supportConstraintLayout = "com.android.support.constraint:constraint-layout:${LibraryVersion.constraintLayout}"
    const val supportAppCompact = "com.android.support:appcompat-v7:${LibraryVersion.supportLibraries}"
    const val supportDesign = "com.android.support:design:${LibraryVersion.supportLibraries}"
    const val supportRecyclerView = "com.android.support:recyclerview-v7:${LibraryVersion.supportLibraries}"

    //jvm test
    const val junit = "junit:junit:${LibraryVersion.junit}"
    const val kluent = "org.amshove.kluent:kluent:${LibraryVersion.kluent}"

    //android test
    const val testRunner = "com.android.support.test:runner:${LibraryVersion.testRunner}"
    const val espressoCore = "com.android.support.test.espresso:espresso-core:${LibraryVersion.espressoCore}"
    const val kluentAndroid = "org.amshove.kluent:kluent-android:${LibraryVersion.kluent}"
}
