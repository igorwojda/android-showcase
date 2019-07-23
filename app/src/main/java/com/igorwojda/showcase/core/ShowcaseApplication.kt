package com.igorwojda.showcase.core

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.igorwojda.showcase.BuildConfig
import com.igorwojda.showcase.appModule
import com.igorwojda.showcase.feature.album.albumModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import timber.log.Timber

/*
False positive "Unused symbol" for a custom Android application class used in AndroidManifest.xml file:
https://youtrack.jetbrains.net/issue/KT-27971
*/
class ShowcaseApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ShowcaseApplication))

        import(appModule)
        import(albumModule)
    }

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        context = this

        initTimber()
        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
