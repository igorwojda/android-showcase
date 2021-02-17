package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.data.database.model.AlbumImageEntity
import com.igorwojda.showcase.feature.album.data.network.enum.AlbumImageSizeJson
import com.igorwojda.showcase.feature.album.data.network.enum.toDomainModel
import com.igorwojda.showcase.feature.album.data.network.enum.toEntityEnum
import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import com.squareup.moshi.Json

internal data class AlbumImageJson(
    @field:Json(name = "#text") val url: String,
    val size: AlbumImageSizeJson
)

internal fun AlbumImageJson.toDomainModel() = AlbumImage(
    url = this.url,
    size = this.size.toDomainModel()
)

internal fun AlbumImageJson.toEntity() =
    this.size.toEntityEnum()?.let { AlbumImageEntity(this.url, it) }
