package com.igorwojda.showcase.feature.album.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumImageDomainModel

@Entity(tableName = "albums")
@TypeConverters(AlbumImageDataEntityTypeConverter::class)
data class AlbumDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mbId: String,
    val name: String,
    val artist: String,
    val images: List<AlbumImageDataEntity> = listOf()
) {

    internal fun toDomainModel() =
        AlbumDomainModel(
            this.name, this.artist,
            this.images.mapNotNull { it.toDomainModel() },
            null, this.mbId
        )
}

data class AlbumImageDataEntity(val url: String, val size: AlbumDataImageSizeEntity) {

    internal fun toDomainModel() =
        this.size.toDomainEnum()?.let { AlbumImageDomainModel(this.url, it) }
}

enum class AlbumDataImageSizeEntity {
    MEDIUM, SMALL, LARGE, EXTRA_LARGE, MEGA;

    internal fun toDomainEnum() =
        AlbumDomainImageSize.values().firstOrNull { it.ordinal == this.ordinal }
}
