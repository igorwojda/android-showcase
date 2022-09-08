package com.igorwojda.showcase.feature.album.data.datasource.database.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumImageEntityModel(
    val url: String,
    val size: AlbumImageSizeEntityModel,
)

internal fun AlbumImageEntityModel.toDomainModel() =
    this.size.toDomainModel()?.let { AlbumImage(this.url, it) }
