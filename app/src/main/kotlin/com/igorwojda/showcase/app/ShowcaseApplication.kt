package com.igorwojda.showcase.app

import android.app.Application
import com.igorwojda.showcase.feature.album.featureAlbumModules
import com.igorwojda.showcase.feature.favourite.featureFavouriteModules
import com.igorwojda.showcase.feature.settings.featureSettingsModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

class ShowcaseApplication : Application() {
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
            modules(featureFavouriteModules)
            modules(featureAlbumModules)
            modules(featureSettingsModules)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
