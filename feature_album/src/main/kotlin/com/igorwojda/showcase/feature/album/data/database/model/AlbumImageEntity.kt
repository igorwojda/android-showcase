package com.igorwojda.showcase.feature.album.data.database.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumImageEntity(
    val url: String,
    val size: AlbumImageSizeEntity,
)

internal fun AlbumImageEntity.toDomainModel() =
    this.size.toDomainModel()?.let { AlbumImage(this.url, it) }
