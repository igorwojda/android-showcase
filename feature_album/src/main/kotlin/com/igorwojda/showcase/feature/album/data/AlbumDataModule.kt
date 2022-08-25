package com.igorwojda.showcase.feature.album.data

import androidx.room.Room
import androidx.room.RoomDatabase
import com.igorwojda.showcase.feature.album.data.database.AlbumDatabase
import com.igorwojda.showcase.feature.album.data.network.service.AlbumRetrofitService
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule = module {

    single { AlbumRepositoryImpl(get(), get()) }

    single { get<Retrofit>().create(AlbumRetrofitService::class.java) }

    single {
        Room.databaseBuilder(
            get(),
            AlbumDatabase::class.java,
            "Albums.db"
        )
    }

    single { get<RoomDatabase.Builder<AlbumDatabase>>().build() }

    single { get<AlbumDatabase>().albums() }
}
