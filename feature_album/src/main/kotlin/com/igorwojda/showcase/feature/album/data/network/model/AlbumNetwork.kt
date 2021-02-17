package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.data.network.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.squareup.moshi.Json

internal data class AlbumNetwork(
    @field:Json(name = "mbid") val mbId: String,
    val name: String,
    val artist: String,
    val wiki: AlbumWikiDataModel?,
    @field:Json(name = "image") val images: List<AlbumImageNetwork>?
)

internal fun AlbumNetwork.toEntity() =
    AlbumEntity(
        mbId = this.mbId,
        name = this.name,
        artist = this.artist,
        images = this.images?.mapNotNull { it.toEntity() } ?: listOf()
    )

internal fun AlbumNetwork.toDomainModel(): Album {
    val images = this.images
        ?.filterNot { it.size == AlbumDataImageSize.UNKNOWN || it.url.isBlank() }
        ?.map { it.toDomainModel() }

    return Album(
        mbId = this.mbId,
        name = this.name,
        artist = this.artist,
        images = images ?: listOf(),
        wiki = this.wiki?.toDomainModel()
    )
}
