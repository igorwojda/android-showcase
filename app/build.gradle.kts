plugins {
    id(GradlePluginId.androidApplication)
    id(GradlePluginId.kotlinAndroid)
    id(GradlePluginId.kotlinAndroidExtensions)
    id(GradlePluginId.ktlintGradle)
}

android {
    compileSdkVersion(ApplicationConfig.compileSdkVersion)

    defaultConfig {
        applicationId = ApplicationConfig.id
        minSdkVersion(ApplicationConfig.minSdkVersion)
        targetSdkVersion(ApplicationConfig.targetSdkVersion)
        buildToolsVersion(ApplicationConfig.buildToolsVersion)

        versionCode = ApplicationConfig.versionCode
        versionName = ApplicationConfig.versionName
        testInstrumentationRunner = ApplicationConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }

        testOptions {
            unitTests.isReturnDefaultValues = true
        }

        testOptions {
            unitTests.isReturnDefaultValues = TestOptions.isReturnDefaultValues
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}

androidExtensions { isExperimental = true }

dependencies {
    // module
    implementation(project(ModuleDependency.featureBase))
    implementation(project(ModuleDependency.featureAlbum))

    // core
    implementation(LibraryDependency.kotlin)
    implementation(LibraryDependency.kodein)
    implementation(LibraryDependency.kodeinAndroidX)
    implementation(LibraryDependency.retrofit)
    implementation(LibraryDependency.retrofitMoshiConverter)
    implementation(LibraryDependency.retrofitCoroutineAdapter)
    implementation(LibraryDependency.stetho)
    implementation(LibraryDependency.stethoOkHttp)
    implementation(LibraryDependency.timber)
    implementation(LibraryDependency.appCompact)
    implementation(LibraryDependency.supportConstraintLayout)
    implementation(LibraryDependency.recyclerView)
    implementation(LibraryDependency.supportMaterial)
    implementation(LibraryDependency.coroutinesAndroid)
    implementation(LibraryDependency.lifecycleExtensions)
//    kapt(LibraryDependency.lifecycleCompiler)
    implementation(LibraryDependency.coordinatorLayout)
    implementation(LibraryDependency.coreKtx)
    implementation(LibraryDependency.fragmentKtx)
    implementation(LibraryDependency.lifecycleViewModelKtx)
    implementation(LibraryDependency.picasso)
    implementation(LibraryDependency.customFloatingActionButton)
    implementation(LibraryDependency.kAndroid)

    // test
    testImplementation(LibraryDependency.junit)
    androidTestImplementation(LibraryDependency.testRunner)
    androidTestImplementation(LibraryDependency.espressoCore)
    testImplementation(LibraryDependency.kluent)
    androidTestImplementation(LibraryDependency.kluentAndroid)
    testImplementation(LibraryDependency.mockitoInline)
    androidTestImplementation(LibraryDependency.mockitoAndroid)
    testImplementation(LibraryDependency.mockitoKotlin)
    testImplementation(LibraryDependency.coroutinesTest)
}
