package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.data.database.model.AlbumImageEntity
import com.igorwojda.showcase.feature.album.data.network.enum.AlbumImageSizeJson
import com.igorwojda.showcase.feature.album.data.network.enum.toDomainModel
import com.igorwojda.showcase.feature.album.data.network.enum.toEntityEnum
import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumImageJson(
    @SerialName("#text") val url: String,
    @SerialName("size") val size: AlbumImageSizeJson,
)

internal fun AlbumImageJson.toDomainModel() = AlbumImage(
    url = this.url,
    size = this.size.toDomainModel()
)

internal fun AlbumImageJson.toEntity() =
    this.size.toEntityEnum()?.let { AlbumImageEntity(this.url, it) }
