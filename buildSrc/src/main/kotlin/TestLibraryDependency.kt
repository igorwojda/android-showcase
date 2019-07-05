private object TestLibraryVersion {
    const val junit = "4.12"
    const val kluent = "1.51"
    const val testRunner = "1.0.2"
    const val espressoCore = "3.0.2"
    const val mockito = "2.28.2"
    const val mockitoKotlin = "2.1.0"
    const val coroutinesAndroid = "1.3.0-M2"
}

object TestLibraryDependency {
    const val junit = "junit:junit:${TestLibraryVersion.junit}"
    const val kluent = "org.amshove.kluent:kluent:${TestLibraryVersion.kluent}"
    const val testRunner = "com.android.support.test:runner:${TestLibraryVersion.testRunner}"
    const val espressoCore = "com.android.support.test.espresso:espresso-core:${TestLibraryVersion.espressoCore}"
    const val kluentAndroid = "org.amshove.kluent:kluent-android:${TestLibraryVersion.kluent}"
    const val mockitoInline = "org.mockito:mockito-inline:${TestLibraryVersion.mockito}"
    const val mockitoAndroid = "org.mockito:mockito-android:${TestLibraryVersion.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${TestLibraryVersion.mockitoKotlin}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestLibraryVersion.coroutinesAndroid}"
}
