package com.igorwojda.lastfm.core

import android.app.Application
import com.github.kittinunf.fuel.core.FuelManager
import com.igorwojda.lastfm.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import timber.log.Timber
import timber.log.Timber.DebugTree

/*
False positive "Unused symbol" for a custom Android application class used in AndroidManifest.xml file:
https://youtrack.jetbrains.net/issue/KT-27971
*/
class LastFmApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {

    }

    override fun onCreate() {

        super.onCreate()

        initTimber()
        initFuel()

//        Kode
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
