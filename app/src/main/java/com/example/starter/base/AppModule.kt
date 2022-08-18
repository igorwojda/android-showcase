package com.example.starter.base

import com.example.starter.BuildConfig
import com.example.starter.data.repository.CustomRepository
import com.example.starter.data.serivce.CustomService
import com.example.starter.presentation.detailscreen.DetailViewModel
import com.example.starter.presentation.listscreen.CustomAdapter
import com.example.starter.presentation.listscreen.ListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    viewModelOf(::ListViewModel)
    factoryOf(::CustomAdapter)

    viewModelOf(::DetailViewModel)

    single { provideRetrofit(get()) }
    single { provideOkHttpClient() }
    single { provideCustomService(get()) }

    factoryOf(::CustomRepository)
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_URL)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

fun provideOkHttpClient(): OkHttpClient = OkHttpClient()
    .newBuilder()
    .build()

fun provideCustomService(retrofit: Retrofit): CustomService = retrofit.create(CustomService::class.java)
