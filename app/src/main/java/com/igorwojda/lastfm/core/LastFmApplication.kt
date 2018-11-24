package com.igorwojda.lastfm.core

import android.app.Application
import androidx.lifecycle.ViewModel
import com.github.kittinunf.fuel.core.FuelManager
import com.igorwojda.lastfm.BuildConfig
import com.igorwojda.lastfm.feature.album.data.repository.AlbumRepositoryImpl
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumListUseCaseImpl
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCaseImpl
import com.igorwojda.lastfm.feature.album.presentation.AlbumDetailsViewModelFactory
import com.igorwojda.lastfm.feature.album.presentation.AlbumListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.Kodein.Builder.TypeBinder
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import timber.log.Timber

/*
False positive "Unused symbol" for a custom Android application class used in AndroidManifest.xml file:
https://youtrack.jetbrains.net/issue/KT-27971
*/
class LastFmApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(albumModule)
    }

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
            Timber.plant(Timber.DebugTree())
        }
    }
}

val albumModule = Kodein.Module("albumModule") {
    import(albumPresentationModule)
    import(albumDomainModule)
    import(albumDataModule)

}

val albumPresentationModule = Kodein.Module("albumPresentationModule") {
    bind() from provider { AlbumListViewModelFactory(instance()) }
    bind() from provider { AlbumDetailsViewModelFactory(instance()) }
}

val albumDomainModule = Kodein.Module("albumDomainModule") {
    bind<GetAlbumListUseCase>() with provider { GetAlbumListUseCaseImpl(instance()) }
    bind<GetAlbumUseCase>() with provider { GetAlbumUseCaseImpl(instance()) }
}

val albumDataModule = Kodein.Module("albumDataModule") {
    bind<AlbumRepository>() with singleton { AlbumRepositoryImpl() }
}

inline fun <reified T : ViewModel> Kodein.Builder.bindViewModel(overrides: Boolean? = null): TypeBinder<T> {
    return bind<T>(T::class.java.simpleName, overrides)
}
