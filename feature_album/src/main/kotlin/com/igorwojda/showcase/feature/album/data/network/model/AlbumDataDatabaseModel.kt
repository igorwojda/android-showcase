package com.igorwojda.showcase.feature.album.data.network.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.igorwojda.showcase.feature.album.data.database.converter.AlbumImageDataEntityTypeConverter
import com.igorwojda.showcase.feature.album.domain.model.Album

@Entity(tableName = "albums")
@TypeConverters(AlbumImageDataEntityTypeConverter::class)
data class AlbumDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mbId: String,
    val name: String,
    val artist: String,
    val images: List<AlbumImageDataEntity> = listOf()
)

internal fun AlbumDataEntity.toDomainModel() =
    Album(
        this.name, this.artist,
        this.images.mapNotNull { it.toDomainModel() },
        null, this.mbId
    )
