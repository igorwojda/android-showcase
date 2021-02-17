package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.data.network.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.album.data.network.enum.toDomainEnum
import com.igorwojda.showcase.feature.album.data.network.enum.toEntityEnum
import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import com.squareup.moshi.Json

internal data class AlbumImageNetwork(
    @field:Json(name = "#text") val url: String,
    val size: AlbumDataImageSize
)

internal fun AlbumImageNetwork.toDomainModel() = AlbumImage(
    url = this.url,
    size = this.size.toDomainEnum()
)

internal fun AlbumImageNetwork.toEntity() =
    this.size.toEntityEnum()?.let { AlbumImageEntity(this.url, it) }
