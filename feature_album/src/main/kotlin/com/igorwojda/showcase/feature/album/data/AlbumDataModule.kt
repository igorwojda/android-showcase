package com.igorwojda.showcase.feature.album.data

import androidx.room.Room
import androidx.room.RoomDatabase
import com.igorwojda.showcase.feature.album.MODULE_NAME
import com.igorwojda.showcase.feature.album.data.repository.AlbumRepositoryImpl
import com.igorwojda.showcase.feature.album.data.retrofit.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.room.AlbumDatabase
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind<AlbumRepository>() with singleton { AlbumRepositoryImpl(instance(), instance()) }

    bind() from singleton { instance<Retrofit>().create(AlbumRetrofitService::class.java) }

    bind<RoomDatabase.Builder<AlbumDatabase>>() with singleton { Room.databaseBuilder(instance(), AlbumDatabase::class.java, "Albums.db") }

    bind<AlbumDatabase>() with singleton { instance<RoomDatabase.Builder<AlbumDatabase>>().build() }

}
