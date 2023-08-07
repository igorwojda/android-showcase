package com.igorwojda.showcase.app

import android.app.Application
import com.google.android.material.color.DynamicColors
import com.igorwojda.showcase.BuildConfig
import com.igorwojda.showcase.base.baseModule
import com.igorwojda.showcase.favourite.featureFavouriteModules
import com.igorwojda.showcase.profile.featureProfilesModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

class ShowcaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
        initDynamicColorScheme()
    }

    private fun initDynamicColorScheme() {
        // Apply dynamic colors to all Activities, Fragments, Views
        // (Material 3 library helper class)
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@ShowcaseApplication)

            modules(appModule)
            modules(baseModule)
            modules(featureFavouriteModules)
            modules(com.igorwojda.showcase.album.featureAlbumModules)
            modules(featureProfilesModules)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
