package com.igorwojda.lastfm.feature.album.data.di

import com.igorwojda.lastfm.feature.album.data.repository.AlbumRepositoryImpl
import com.igorwojda.lastfm.feature.album.data.retrofit.service.AlbumRetrofitService
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val albumDataModule = Kodein.Module("albumDataModule") {
    bind<AlbumRepository>() with singleton { AlbumRepositoryImpl(instance()) }
    bind() from singleton { instance<Retrofit>().create(AlbumRetrofitService::class.java) }
}
