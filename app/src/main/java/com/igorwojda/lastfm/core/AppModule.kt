package com.igorwojda.lastfm.core

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// w base module?
const val LAST_FM_API_BASE_URL = "http://ws.audioscrobbler.com/2.0/"

val appModule = Kodein.Module("baseDataModule") {
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

    bind() from singleton {
        Picasso.get()
    }
}
