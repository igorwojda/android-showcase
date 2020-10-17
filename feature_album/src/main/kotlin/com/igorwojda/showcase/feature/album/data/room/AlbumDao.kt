package com.igorwojda.showcase.feature.album.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.igorwojda.showcase.feature.album.data.room.entity.AlbumDataEntity

@Dao
interface AlbumDao {

    @Query("SELECT * FROM albums")
    suspend fun getAll(): List<AlbumDataEntity>

    @Query("SELECT * FROM albums where artist = :artistName and name = :albumName and mbId = :mbId")
    suspend fun getAlbum(artistName: String, albumName: String, mbId: String?): AlbumDataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<AlbumDataEntity>)

}
