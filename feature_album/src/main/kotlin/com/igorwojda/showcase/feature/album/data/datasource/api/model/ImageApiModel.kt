package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageEntityModel
import com.igorwojda.showcase.feature.album.domain.model.Image
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ImageApiModel(
    @SerialName("#text") val url: String,
    @SerialName("size") val size: ImageSizeApiModel,
)

internal fun ImageApiModel.toDomainModel() = Image(
    url = this.url,
    size = this.size.toDomainModel()
)

internal fun ImageApiModel.toEntityModel() =
    this.size.toEntityModel()?.let { AlbumImageEntityModel(this.url, it) }
