package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.data.database.model.AlbumEntity
import com.igorwojda.showcase.feature.album.data.network.enum.AlbumImageSizeJson
import com.igorwojda.showcase.feature.album.domain.model.Album
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumJson(
    @SerialName("mbid") val mbId: String? = null,
    @SerialName("name") val name: String,
    @SerialName("artist") val artist: String,
    @SerialName("wiki") val wiki: AlbumWikiJson? = null,
    @SerialName("image") val images: List<AlbumImageJson>? = null,
)

internal fun AlbumJson.toEntity() =
    AlbumEntity(
        mbId = this.mbId ?: "",
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
