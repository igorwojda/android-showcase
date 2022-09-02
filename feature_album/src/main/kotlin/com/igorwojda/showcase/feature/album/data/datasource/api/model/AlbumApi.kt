package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumEntity
import com.igorwojda.showcase.feature.album.domain.model.Album
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumApi(
    @SerialName("mbid") val mbId: String? = null,
    @SerialName("name") val name: String,
    @SerialName("artist") val artist: String,
    @SerialName("wiki") val wiki: AlbumWikiApi? = null,
    @SerialName("image") val images: List<AlbumImageApi>? = null,
)

internal fun AlbumApi.toEntity() =
    AlbumEntity(
        mbId = this.mbId ?: "",
        name = this.name,
        artist = this.artist,
        images = this.images?.mapNotNull { it.toEntity() } ?: listOf()
    )

internal fun AlbumApi.toDomainModel(): Album {
    val images = this.images
        ?.filterNot { it.size == AlbumImageSizeApi.UNKNOWN || it.url.isBlank() }
        ?.map { it.toDomainModel() }

    return Album(
        mbId = this.mbId,
        name = this.name,
        artist = this.artist,
        images = images ?: listOf(),
        wiki = this.wiki?.toDomainModel()
    )
}
