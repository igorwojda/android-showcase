package com.igorwojda.lastfm.core

import okhttp3.Interceptor
import okhttp3.Response

class LastFmRequestInterceptor(private val apiKey: String) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
        val url = it.url().newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("format", "json")
            .build()

        val newRequest = it.newBuilder()
            .url(url)
            .build()

        chain.proceed(newRequest)
    }
}
