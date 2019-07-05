object AndroidConfig {
    const val compileSdkVersion = 28
    const val minSdkVersion = 21
    const val targetSdkVersion = 28
    const val buildToolsVersion = "28.0.3"

    const val versionCode = 1
    const val versionName = "1.0"

    const val id = "com.igorwojda.lastfm"
    const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    const val supportLibraryVectorDrawables = true
}

interface BuildType {
    val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = false
}

object TestOptions {
    const val IS_RETURN_DEFAULT_VALUES = true
}
