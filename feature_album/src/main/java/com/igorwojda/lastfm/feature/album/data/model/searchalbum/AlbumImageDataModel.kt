package com.igorwojda.lastfm.feature.album.data.model.searchalbum

import com.igorwojda.lastfm.feature.album.data.model.enum.AlbumDataImageSize
import com.igorwojda.lastfm.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.lastfm.feature.album.domain.model.searchalbum.AlbumImageDomainModel
import com.squareup.moshi.Json

data class AlbumImageDataModel(
    @field:Json(name = "#text") val url: String,
    val size: AlbumDataImageSize
)

fun AlbumImageDataModel.toDomainModel() = AlbumImageDomainModel(
    url = this.url,
    size = AlbumDomainImageSize.valueOf(this.size.name)
)
