package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageEntity
import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumImageApi(
    @SerialName("#text") val url: String,
    @SerialName("size") val size: AlbumImageSizeApi,
)

internal fun AlbumImageApi.toDomainModel() = AlbumImage(
    url = this.url,
    size = this.size.toDomainModel()
)

internal fun AlbumImageApi.toEntity() =
    this.size.toEntityEnum()?.let { AlbumImageEntity(this.url, it) }
