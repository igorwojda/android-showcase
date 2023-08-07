package com.igorwojda.showcase.album.data

import com.igorwojda.showcase.album.data.datasource.api.model.AlbumApiModel
import com.igorwojda.showcase.album.data.datasource.api.model.AlbumListApiModel
import com.igorwojda.showcase.album.data.datasource.api.model.ImageApiModel
import com.igorwojda.showcase.album.data.datasource.api.model.ImageSizeApiModel
import com.igorwojda.showcase.album.data.datasource.api.model.SearchAlbumResultsApiModel
import com.igorwojda.showcase.album.data.datasource.api.model.TagApiModel
import com.igorwojda.showcase.album.data.datasource.api.model.TagListApiModel
import com.igorwojda.showcase.album.data.datasource.api.model.TrackApiModel
import com.igorwojda.showcase.album.data.datasource.api.model.TrackListApiModel
import com.igorwojda.showcase.album.data.datasource.api.response.SearchAlbumResponse
import com.igorwojda.showcase.album.data.datasource.database.model.AlbumEntityModel
import com.igorwojda.showcase.album.data.datasource.database.model.ImageEntityModel
import com.igorwojda.showcase.album.data.datasource.database.model.ImageSizeEntityModel
import com.igorwojda.showcase.album.data.datasource.database.model.TagEntityModel
import com.igorwojda.showcase.album.data.datasource.database.model.TrackEntityModel

object DataFixtures {

    internal fun getAlbumsApiModel() = listOf(
        getAlbumApiModel("mbid1", "album1", "artist1"),
    )

    internal fun getAlbumsEntityModels() = listOf(
        getAlbumEntityModel(1, "mbid1", "album1", "artist1"),
        getAlbumEntityModel(2, "mbid2", "album2", "artist2"),
    )

    internal fun getAlbumApiModel(
        mbId: String = "mbId",
        name: String = "album",
        artist: String = "artist",
        images: List<ImageApiModel>? = listOf(getImageModelApiModel()),
        tracks: TrackListApiModel = TrackListApiModel(getTrackModelApiModel()),
        tags: TagListApiModel = TagListApiModel(getTagModelApiModel()),
    ): AlbumApiModel = AlbumApiModel(
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

    private fun getTagModelApiModel(
        name: String = "tag",
    ) = listOf(TagApiModel(name))

    private fun getAlbumEntityModel(
        id: Int = 0,
        mbId: String = "mbId",
        name: String = "album",
        artist: String = "artist",
        images: List<ImageEntityModel> = listOf(getImageEntityModel()),
        tracks: List<TrackEntityModel> = listOf(getTrackEntityModel()),
        tags: List<TagEntityModel> = listOf(getTagEntityModel()),
    ): AlbumEntityModel = AlbumEntityModel(
        id,
        mbId,
        name,
        artist,
        images,
        tracks,
        tags,
    )

    private fun getImageEntityModel(
        url: String = "url_${ImageSizeApiModel.EXTRA_LARGE}",
        size: ImageSizeEntityModel = ImageSizeEntityModel.EXTRA_LARGE,
    ) = ImageEntityModel(url, size)

    private fun getTrackEntityModel(
        name: String = "track",
        duration: Int = 12,
    ) = TrackEntityModel(name, duration)

    private fun getTagEntityModel(
        name: String = "tag",
    ) = TagEntityModel(name)

    object ApiResponse {
        internal fun getSearchAlbum() = SearchAlbumResponse(
            SearchAlbumResultsApiModel(
                AlbumListApiModel(getAlbumsApiModel()),
            ),
        )
    }
}
