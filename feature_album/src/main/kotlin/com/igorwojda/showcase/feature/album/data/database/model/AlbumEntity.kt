package com.igorwojda.showcase.feature.album.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.igorwojda.showcase.feature.album.data.database.converter.AlbumImageDataEntityTypeConverter
import com.igorwojda.showcase.feature.album.domain.model.Album

@Entity(tableName = "albums")
@TypeConverters(AlbumImageDataEntityTypeConverter::class)
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mbId: String,
    val name: String,
    val artist: String,
    val images: List<AlbumImageEntity> = listOf()
)

internal fun AlbumEntity.toDomainModel() =
    Album(
        this.name, this.artist,
        this.images.mapNotNull { it.toDomainModel() },
        null, this.mbId
    )
