package com.igorwojda.lastfm.core

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.igorwojda.lastfm.BuildConfig
import com.igorwojda.lastfm.feature.album.data.repository.AlbumRepositoryImpl
import com.igorwojda.lastfm.feature.album.data.retrofit.service.AlbumRetrofitService
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCaseImpl
import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCase
import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCaseImpl
import com.igorwojda.lastfm.feature.album.presentation.AlbumListViewModelFactory
import com.igorwojda.lastfm.feature.album.presentation.recyclerview.AlbumAdapter
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.picasso.Picasso
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

        bind<Context>() with instance(this@LastFmApplication)
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

val albumModule = Kodein.Module("albumModule") {
    import(albumPresentationModule)
    import(albumDomainModule)
    import(albumDataModule)
}

val albumPresentationModule = Kodein.Module("albumPresentationModule") {
    bind() from provider { AlbumListViewModelFactory(instance()) }
    bind() from singleton { AlbumAdapter(instance()) }
}

val albumDomainModule = Kodein.Module("albumDomainModule") {
    bind<SearchAlbumUseCase>() with provider { SearchAlbumUseCaseImpl(instance()) }
    bind<GetAlbumUseCase>() with provider { GetAlbumUseCaseImpl(instance()) }
}

val albumDataModule = Kodein.Module("albumDataModule") {
    bind<AlbumRepository>() with singleton { AlbumRepositoryImpl(instance()) }
    bind() from singleton { instance<Retrofit>().create(AlbumRetrofitService::class.java) }
}

val baseModule = Kodein.Module("baseModule") {
    import(baseDataModule)
    import(basePresentationModule)
}

// w base module?
const val LAST_FM_API_BASE_URL = "http://ws.audioscrobbler.com/2.0/"

val basePresentationModule = Kodein.Module("basePresentationModule") {
    bind() from singleton {
        Picasso.get()
    }
}

val baseDataModule = Kodein.Module("baseDataModule") {
    // In production apiKey would be provided by CI
    // Typically we don't want to store such key in public repository, however this is just a sample project, so
    // we can favour convenience (app can be compiled and launched after checkout) over security (each person who
    // checkouts the project must generate own api key and change app configuration before running it).
    bind() from singleton { LastFmRequestInterceptor("70696db59158cb100370ad30a7a705c1") }


    bind<OkHttpClient>() with singleton {
        val lastFmRequestInterceptor = instance<LastFmRequestInterceptor>()

        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(lastFmRequestInterceptor)
            .build()
    }

    bind<Retrofit>() with singleton {
        val okHttpClient: OkHttpClient = instance()
        Retrofit.Builder()
            .baseUrl(LAST_FM_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }
}
