package com.igorwojda.showcase.album.data.datasource.api.model

import com.igorwojda.showcase.album.data.datasource.database.model.AlbumEntityModel
import com.igorwojda.showcase.album.domain.model.Album
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumApiModel(
    @SerialName("mbid") val mbId: String? = null,
    @SerialName("name") val name: String,
    @SerialName("artist") val artist: String,
    @SerialName("image") val images: List<ImageApiModel>? = null,
    @SerialName("tracks") val tracks: TrackListApiModel? = null,
    @SerialName("tags") val tags: TagListApiModel? = null,
)

internal fun AlbumApiModel.toEntityModel() =
    AlbumEntityModel(
        mbId = this.mbId ?: "",
        name = this.name,
        artist = this.artist,
        images = this.images?.mapNotNull { it.toEntityModel() } ?: listOf(),
        tracks = this.tracks?.track?.map { it.toEntityModel() },
        tags = this.tags?.tag?.map { it.toEntityModel() },
    )

internal fun AlbumApiModel.toDomainModel(): Album {
    val images = this.images
        ?.filterNot { it.size == ImageSizeApiModel.UNKNOWN || it.url.isBlank() }
        ?.map { it.toDomainModel() }

    return Album(
        mbId = this.mbId,
        name = this.name,
        artist = this.artist,
        images = images ?: listOf(),
        tracks = this.tracks?.track?.map { it.toDomainModel() },
        tags = this.tags?.tag?.map { it.toDomainModel() },
    )
}
