package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumRoomModel
import com.igorwojda.showcase.feature.album.domain.model.Album
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

internal fun AlbumApiModel.toRoomModel() =
    AlbumRoomModel(
        mbId = this.mbId ?: "",
        name = this.name,
        artist = this.artist,
        images = this.images?.mapNotNull { it.toRoomModel() } ?: listOf(),
        tracks = this.tracks?.track?.map { it.toRoomModel() },
        tags = this.tags?.tag?.map { it.toRoomModel() },
    )

internal fun AlbumApiModel.toDomainModel(): Album {
    val images =
        this.images
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
