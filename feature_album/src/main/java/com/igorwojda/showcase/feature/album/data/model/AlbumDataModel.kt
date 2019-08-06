package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.data.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.squareup.moshi.Json

internal data class AlbumDataModel(
    @field:Json(name = "mbid") val mbId: String?,
    val name: String,
    val artist: String,
    val wiki: AlbumWikiDataModel?,
    @field:Json(name = "image") val images: List<AlbumImageDataModel>?
)

internal fun AlbumDataModel.toDomainModel() = AlbumDomainModel(
    mbId = this.mbId,
    name = this.name,
    artist = this.artist,
    images = if(this.images != null) this.images.toDomainModel() else listOf(),
    wiki = this.wiki?.toDomainModel()
)

internal fun List<AlbumImageDataModel>.toDomainModel() =
    filter { it.size != AlbumDataImageSize.UNKNOWN }
        .filterNot { it.url.isBlank() }
        .map { it.toDomainModel() }
