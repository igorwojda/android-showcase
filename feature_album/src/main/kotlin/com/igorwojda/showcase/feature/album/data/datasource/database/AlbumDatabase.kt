package com.igorwojda.showcase.feature.album.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
internal abstract class AlbumDatabase : RoomDatabase() {

    abstract fun albums(): AlbumDao
}
