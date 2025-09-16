package com.igorwojda.showcase.feature.album.data

import androidx.room.Room
import com.igorwojda.showcase.feature.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.datasource.database.AlbumDatabase
import com.igorwojda.showcase.feature.album.data.mapper.AlbumMapper
import com.igorwojda.showcase.feature.album.data.mapper.ImageMapper
import com.igorwojda.showcase.feature.album.data.mapper.ImageSizeMapper
import com.igorwojda.showcase.feature.album.data.mapper.TagMapper
import com.igorwojda.showcase.feature.album.data.mapper.TrackMapper
import com.igorwojda.showcase.feature.album.data.repository.AlbumRepositoryImpl
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

internal val dataModule =
    module {

        singleOf(::AlbumRepositoryImpl) { bind<AlbumRepository>() }

        single { get<Retrofit>().create(AlbumRetrofitService::class.java) }

        single {
            Room
                .databaseBuilder(
                    get(),
                    AlbumDatabase::class.java,
                    "Albums.db",
                ).build()
        }

        single { get<AlbumDatabase>().albums() }

        singleOf(::ImageSizeMapper)
        singleOf(::ImageMapper)
        singleOf(::TrackMapper)
        singleOf(::TagMapper)
        singleOf(::AlbumMapper)
    }
