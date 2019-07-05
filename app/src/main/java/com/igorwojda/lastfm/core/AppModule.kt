package com.igorwojda.lastfm.core

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.igorwojda.lastfm.R
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.bindings.Provider
import org.kodein.di.erased
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val TAG_API_BASE_URL = "TAG_API_BASE_URL"
private const val TAG_API_TOKEN = "TAG_API_TOKEN"

val appModule = Kodein.Module("baseDataModule") {

    // build parameters
    bind(TAG_API_BASE_URL) from Provider(erased<Context>(), erased()) {
        context.getString(R.string.api_base_url)
    }

    bind(TAG_API_TOKEN) from Provider(erased<Context>(), erased()) {
        context.getString(R.string.api_token)
    }

    // In production apiKey would be provided by CI
    // Typically we don't want to store such key in public repository, however this is just a sample project, so
    // we can favour convenience (app can be compiled and launched after checkout) over security (each person who
    // checkouts the project must generate own api key and change app configuration before running it).
    bind() from singleton {
        AuthenticationInterceptor(instance(TAG_API_TOKEN))
    }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        val lastFmRequestInterceptor = instance<AuthenticationInterceptor>()

        instance<OkHttpClient.Builder>()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(lastFmRequestInterceptor)
            .build()
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(instance<String>(TAG_API_BASE_URL))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(instance())
            .build()
    }

    bind() from singleton {
        Picasso.get()
    }
}
