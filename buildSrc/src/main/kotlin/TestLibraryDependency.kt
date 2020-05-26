private object TestLibraryVersion {
    const val JUNIT = "4.13"
    const val KLUENT = "1.61"
    const val TEST_RUNNER = "1.0.2"
    const val ESPRESSO_CORE = "3.0.2"
    const val MOCKK = "1.10.0"
    const val ARCH = "2.1.0"
}

object TestLibraryDependency {
    const val JUNIT = "junit:junit:${TestLibraryVersion.JUNIT}"
    const val KLUENT_ANDROID = "org.amshove.kluent:kluent-android:${TestLibraryVersion.KLUENT}"
    const val KLUENT = "org.amshove.kluent:kluent:${TestLibraryVersion.KLUENT}"
    const val TEST_RUNNER = "com.android.support.test:runner:${TestLibraryVersion.TEST_RUNNER}"
    const val ESPRESSO_CORE = "com.android.support.test.espresso:espresso-core:${TestLibraryVersion.ESPRESSO_CORE}"
    const val MOCKK = "io.mockk:mockk:${TestLibraryVersion.MOCKK}"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${CoreVersion.COROUTINES_ANDROID}"
    const val ANDROID_X_TEST = "androidx.arch.core:core-testing:${TestLibraryVersion.ARCH}"
}
