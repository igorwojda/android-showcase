package com.igorwojda.showcase

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.igorwojda.showcase.app.data.retrofit.AuthenticationInterceptor
import com.igorwojda.showcase.app.data.retrofit.UserAgentInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal const val MODULE_NAME = "App"

val appModule = Kodein.Module("${MODULE_NAME}Module") {

    bind() from singleton { AuthenticationInterceptor(BuildConfig.GRADLE_API_TOKEN) }

    bind() from singleton { UserAgentInterceptor() }

    bind<HttpLoggingInterceptor>() with singleton {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

    bind<OkHttpClient.Builder>() with singleton { OkHttpClient.Builder() }

    bind<OkHttpClient>() with singleton {
        instance<OkHttpClient.Builder>()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(instance<AuthenticationInterceptor>())
            .addInterceptor(instance<UserAgentInterceptor>())
            .addInterceptor(instance<HttpLoggingInterceptor>())
            .build()
    }

    bind<Retrofit>() with singleton {
        instance<Retrofit.Builder>()
            .baseUrl(BuildConfig.GRADLE_API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(instance())
            .build()
    }
}
