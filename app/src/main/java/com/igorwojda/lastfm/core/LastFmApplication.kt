package com.igorwojda.lastfm.core

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager
import com.igorwojda.lastfm.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

class LastFmApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initTimber()
        initFuel()
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
