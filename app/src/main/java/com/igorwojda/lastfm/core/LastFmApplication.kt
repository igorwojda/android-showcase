package com.igorwojda.lastfm.core

import android.app.Application
import com.igorwojda.lastfm.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class LastFmApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
