package com.igorwojda.showcase.app

import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.igorwojda.showcase.BuildConfig
import com.igorwojda.showcase.app.feature.FeatureManager
import com.igorwojda.showcase.appModule
import com.igorwojda.showcase.base.baseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

/*
False positive "Unused symbol" for a custom Android application class referenced in AndroidManifest.xml file:
https://youtrack.jetbrains.net/issue/KT-27971
*/
class ShowcaseApplication : SplitCompatApplication() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@ShowcaseApplication)
            modules(appModule)
            modules(baseModule)
            modules(FeatureManager.koinModules)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
