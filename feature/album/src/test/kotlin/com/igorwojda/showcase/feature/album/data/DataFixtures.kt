package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumListApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageSizeApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.SearchAlbumResultsApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TagApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TagListApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TrackApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.TrackListApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.response.SearchAlbumResponse
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumRoomModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageRoomModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageSizeRoomModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.TagRoomModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.TrackRoomModel

object DataFixtures {
    internal fun getAlbumsApiModel() =
        listOf(
            getAlbumApiModel("mbid1", "album1", "artist1"),
        )

    internal fun getAlbumsRoomModels() =
        listOf(
            getAlbumRoomModel(1, "mbid1", "album1", "artist1"),
            getAlbumRoomModel(2, "mbid2", "album2", "artist2"),
        )

    internal fun getAlbumApiModel(
        mbId: String = "mbId",
        name: String = "album",
        artist: String = "artist",
        images: List<ImageApiModel>? = listOf(getImageModelApiModel()),
        tracks: TrackListApiModel = TrackListApiModel(getTrackModelApiModel()),
        tags: TagListApiModel = TagListApiModel(getTagModelApiModel()),
    ): AlbumApiModel =
        AlbumApiModel(
            mbId,
            name,
            artist,
            images,
            tracks,
            tags,
        )

    internal fun getImageModelApiModel(
        url: String = "url_${ImageSizeApiModel.EXTRA_LARGE}",
        size: ImageSizeApiModel = ImageSizeApiModel.EXTRA_LARGE,
    ) = ImageApiModel(url, size)

    private fun getTrackModelApiModel(
        name: String = "track",
        duration: Int? = 12,
    ) = listOf(TrackApiModel(name, duration))

    private fun getTagModelApiModel(name: String = "tag") = listOf(TagApiModel(name))

    private fun getAlbumRoomModel(
        id: Int = 0,
        mbId: String = "mbId",
        name: String = "album",
        artist: String = "artist",
        images: List<ImageRoomModel> = listOf(getImageRoomModel()),
        tracks: List<TrackRoomModel> = listOf(getTrackRoomModel()),
        tags: List<TagRoomModel> = listOf(getTagRoomModel()),
    ): AlbumRoomModel =
        AlbumRoomModel(
            id,
            mbId,
            name,
            artist,
            images,
            tracks,
            tags,
        )

    private fun getImageRoomModel(
        url: String = "url_${ImageSizeApiModel.EXTRA_LARGE}",
        size: ImageSizeRoomModel = ImageSizeRoomModel.EXTRA_LARGE,
    ) = ImageRoomModel(url, size)

    private fun getTrackRoomModel(
        name: String = "track",
        duration: Int = 12,
    ) = TrackRoomModel(name, duration)

    private fun getTagRoomModel(name: String = "tag") = TagRoomModel(name)

    object ApiResponse {
        internal fun getSearchAlbum() =
            SearchAlbumResponse(
                SearchAlbumResultsApiModel(
                    AlbumListApiModel(getAlbumsApiModel()),
                ),
            )
    }
}
