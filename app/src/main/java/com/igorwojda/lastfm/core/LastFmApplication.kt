package com.igorwojda.lastfm.core

import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.igorwojda.lastfm.BuildConfig
import com.igorwojda.lastfm.feature.album.data.repository.AlbumRepositoryImpl
import com.igorwojda.lastfm.feature.album.data.retrofit.AlbumRetrofitService
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCaseImpl
import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCase
import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCaseImpl
import com.igorwojda.lastfm.feature.album.presentation.AlbumListViewModelFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

/*
False positive "Unused symbol" for a custom Android application class used in AndroidManifest.xml file:
https://youtrack.jetbrains.net/issue/KT-27971
*/
class LastFmApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(albumModule)
        import(baseModule)
    }

    override fun onCreate() {
        super.onCreate()

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

val albumModule = Kodein.Module("albumModule") {
    import(albumPresentationModule)
    import(albumDomainModule)
    import(albumDataModule)
}

val albumPresentationModule = Kodein.Module("albumPresentationModule") {
    bind() from provider { AlbumListViewModelFactory(instance()) }
}

val albumDomainModule = Kodein.Module("albumDomainModule") {
    bind<SearchAlbumUseCase>() with provider { SearchAlbumUseCaseImpl(instance()) }
    bind<GetAlbumUseCase>() with provider { GetAlbumUseCaseImpl(instance()) }
}

val albumDataModule = Kodein.Module("albumDataModule") {
    bind<AlbumRepository>() with singleton { AlbumRepositoryImpl(instance()) }

    bind<AlbumRetrofitService>() with singleton {
        instance<Retrofit>().let { it.create(AlbumRetrofitService::class.java) }
    }
}

val baseModule = Kodein.Module("baseModule") {
    import(baseDataModule)
}

// w base module?
const val LAST_FM_API_BASE_URL = "http://ws.audioscrobbler.com/2.0/"

val baseDataModule = Kodein.Module("baseDataModule") {
    bind<Retrofit>() with singleton {
        val okHttpClient: OkHttpClient = instance()

        Retrofit.Builder()
            .baseUrl(LAST_FM_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    bind<OkHttpClient>() with singleton {
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }
}
