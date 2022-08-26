package com.igorwojda.showcase.feature.album.data

import androidx.room.Room
import com.igorwojda.showcase.feature.album.data.database.AlbumDatabase
import com.igorwojda.showcase.feature.album.data.network.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<AlbumRepository> { AlbumRepositoryImpl(get(), get()) }

    single { get<Retrofit>().create(AlbumRetrofitService::class.java) }

    single {
        Room.databaseBuilder(
            get(),
            AlbumDatabase::class.java,
            "Albums.db"
        ).build()
    }

    single { get<AlbumDatabase>().albums() }
}
