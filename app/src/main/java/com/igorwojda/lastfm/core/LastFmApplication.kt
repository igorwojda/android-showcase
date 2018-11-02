package com.igorwojda.lastfm.core

import android.app.Application
import com.facebook.stetho.Stetho
import com.github.kittinunf.fuel.core.FuelManager
import com.igorwojda.lastfm.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class LastFmApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initTimber()
        initFuel()
        initStetho()
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun initFuel() {
        FuelManager.instance.apply {
            basePath = "https://jsonplaceholder.typicode.com"
            baseHeaders = mapOf("Device" to "Android")
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}
