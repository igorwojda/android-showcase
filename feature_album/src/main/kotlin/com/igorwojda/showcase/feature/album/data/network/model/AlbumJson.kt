package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.data.database.model.AlbumEntity
import com.igorwojda.showcase.feature.album.data.network.enum.AlbumImageSizeJson
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.squareup.moshi.Json

internal data class AlbumJson(
    @field:Json(name = "mbid") val mbId: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "artist") val artist: String,
    @field:Json(name = "wiki") val wiki: AlbumWikiJson?,
    @field:Json(name = "image") val images: List<AlbumImageJson>?
)

internal fun AlbumJson.toEntity() =
    AlbumEntity(
        mbId = this.mbId,
        name = this.name,
        artist = this.artist,
        images = this.images?.mapNotNull { it.toEntity() } ?: listOf()
    )

internal fun AlbumJson.toDomainModel(): Album {
    val images = this.images
        ?.filterNot { it.size == AlbumImageSizeJson.UNKNOWN || it.url.isBlank() }
        ?.map { it.toDomainModel() }

    return Album(
        mbId = this.mbId,
        name = this.name,
        artist = this.artist,
        images = images ?: listOf(),
        wiki = this.wiki?.toDomainModel()
    )
}
