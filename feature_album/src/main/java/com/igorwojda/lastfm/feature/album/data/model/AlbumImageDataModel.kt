package com.igorwojda.lastfm.feature.album.data.model

import com.igorwojda.lastfm.feature.album.data.enum.AlbumDataImageSize
import com.igorwojda.lastfm.feature.album.data.enum.toDomainEnum
import com.igorwojda.lastfm.feature.album.domain.model.AlbumImageDomainModel
import com.squareup.moshi.Json

internal data class AlbumImageDataModel(
    @field:Json(name = "#text") val url: String,
    val size: AlbumDataImageSize
)

internal fun AlbumImageDataModel.toDomainModel() = AlbumImageDomainModel(
    url = this.url,
    size = this.size.toDomainEnum()
)
