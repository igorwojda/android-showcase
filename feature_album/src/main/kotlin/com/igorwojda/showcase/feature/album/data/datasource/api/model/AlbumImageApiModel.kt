package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageEntityModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumImageApiModel(
    @SerialName("#text") val url: String,
    @SerialName("size") val size: AlbumImageSizeApiModel,
)

internal fun AlbumImageApiModel.toDomainModel() = AlbumImage(
    url = this.url,
    size = this.size.toDomainModel()
)

internal fun AlbumImageApiModel.toEntityModel() =
    this.size.toEntityModel()?.let { AlbumImageEntityModel(this.url, it) }
