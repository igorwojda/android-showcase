package com.igorwojda.showcase.feature.album.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.igorwojda.showcase.feature.album.data.network.model.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albums(): AlbumDao
}
