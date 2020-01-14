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

internal fun AlbumDataModel.toDomainModel(): AlbumDomainModel {
    val images = this.images
        ?.filterNot { it.size == AlbumDataImageSize.UNKNOWN || it.url.isBlank() }
        ?.map { it.toDomainModel() }

    return AlbumDomainModel(
        mbId = this.mbId,
        name = this.name,
        artist = this.artist,
        images = images ?: listOf(),
        wiki = this.wiki?.toDomainModel()
    )
}
