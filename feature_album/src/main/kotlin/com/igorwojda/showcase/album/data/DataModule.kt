package com.igorwojda.showcase.album.data

import androidx.room.Room
import com.igorwojda.showcase.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.album.data.datasource.database.AlbumDatabase
import com.igorwojda.showcase.album.data.repository.AlbumRepositoryImpl
import com.igorwojda.showcase.album.domain.repository.AlbumRepository
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single<AlbumRepository> { AlbumRepositoryImpl(get(), get()) }

    single { get<Retrofit>().create(AlbumRetrofitService::class.java) }

    single {
        Room.databaseBuilder(
            get(),
            AlbumDatabase::class.java,
            "Albums.db",
        ).build()
    }

    single { get<AlbumDatabase>().albums() }
}
