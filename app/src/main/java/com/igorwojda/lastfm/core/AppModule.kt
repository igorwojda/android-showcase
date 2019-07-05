package com.igorwojda.lastfm.core

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.igorwojda.lastfm.R
import com.igorwojda.lastfm.core.retrofit.AuthenticationInterceptor
import com.igorwojda.lastfm.core.retrofit.UserAgentInterceptor
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

    bind() from singleton {
        AuthenticationInterceptor(instance(TAG_API_TOKEN))
    }

    bind() from singleton { UserAgentInterceptor() }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(instance<AuthenticationInterceptor>())
            .addInterceptor(instance<UserAgentInterceptor>())
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
