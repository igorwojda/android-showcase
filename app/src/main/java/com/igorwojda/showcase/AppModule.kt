package com.igorwojda.showcase

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.igorwojda.showcase.app.FeatureManager
import com.igorwojda.showcase.app.data.retrofit.AuthenticationInterceptor
import com.igorwojda.showcase.app.data.retrofit.UserAgentInterceptor
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

val appModule = Kodein.Module("appModule") {

    bind(TAG_API_BASE_URL) from Provider(
        erased<Context>(),
        erased()
    ) { context.getString(R.string.build_param_api_base_url) }

    bind(TAG_API_TOKEN) from Provider(erased<Context>(), erased()) { context.getString(R.string.build_param_api_token) }

    bind() from singleton { AuthenticationInterceptor(instance(TAG_API_TOKEN)) }

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
            .baseUrl(instance<String>(TAG_API_BASE_URL))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(instance())
            .build()
    }

    bind() from singleton { Picasso.get() }

    bind() from singleton { FeatureManager.albumGateway }
}
