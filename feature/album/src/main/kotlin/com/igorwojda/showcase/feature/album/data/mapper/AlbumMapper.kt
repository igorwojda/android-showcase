package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageSizeApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumRoomModel
import com.igorwojda.showcase.feature.album.domain.model.Album

internal class AlbumMapper(
    private val imageMapper: ImageMapper,
    private val trackMapper: TrackMapper,
    private val tagMapper: TagMapper,
) {
    fun apiToRoom(apiModel: AlbumApiModel) =
        AlbumRoomModel(
            mbId = apiModel.mbId ?: "",
            name = apiModel.name,
            artist = apiModel.artist,
            images = apiModel.images?.mapNotNull { imageMapper.apiToRoom(it) } ?: listOf(),
            tracks = apiModel.tracks?.track?.map { trackMapper.apiToRoom(it) },
            tags = apiModel.tags?.tag?.map { tagMapper.apiToRoom(it) },
        )

    fun apiToDomain(apiModel: AlbumApiModel): Album {
        val images =
            apiModel.images
                ?.filterNot { it.size == ImageSizeApiModel.UNKNOWN || it.url.isBlank() }
                ?.map { imageMapper.apiToDomain(it) }

        return Album(
            mbId = apiModel.mbId,
            name = apiModel.name,
            artist = apiModel.artist,
            images = images ?: listOf(),
            tracks = apiModel.tracks?.track?.map { trackMapper.apiToDomain(it) },
            tags = apiModel.tags?.tag?.map { tagMapper.apiToDomain(it) },
        )
    }

    fun roomToDomain(roomModel: AlbumRoomModel) =
        Album(
            roomModel.name,
            roomModel.artist,
            roomModel.mbId,
            roomModel.images.mapNotNull { imageMapper.roomToDomain(it) },
            roomModel.tracks?.map { trackMapper.roomToDomain(it) },
            roomModel.tags?.map { tagMapper.roomToDomain(it) },
        )
}
