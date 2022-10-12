package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumImageApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumImageSizeApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumListApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumSearchApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumWikiApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.response.SearchAlbumResponse
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumEntityModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageEntityModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageSizeEntityModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumTagEntityModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumTrackEntityModel

object DataFixtures {

    internal fun getAlbumsApiModels() = listOf(
        getAlbumApiModel("mbid1", "album1", "artist1")
    )

    internal fun getAlbumsEntityModels() = listOf(
        getAlbumEntityModel(1, "mbid1", "album1", "artist1"),
        getAlbumEntityModel(2, "mbid2", "album2", "artist2")
    )

    internal fun getAlbumApiModel(
        mbId: String = "mbId",
        name: String = "album",
        artist: String = "artist",
        wiki: AlbumWikiApiModel? = getAlbumWiki(),
        images: List<AlbumImageApiModel>? = listOf(getAlbumImageModel()),
    ): AlbumApiModel = AlbumApiModel(
        mbId,
        name,
        artist,
        wiki,
        images
    )

    internal fun getAlbumImageModel(
        url: String = "url_${AlbumImageSizeApiModel.EXTRA_LARGE}",
        size: AlbumImageSizeApiModel = AlbumImageSizeApiModel.EXTRA_LARGE,
    ) = AlbumImageApiModel(
        url,
        size
    )

    internal fun getAlbumWiki(
        published: String = "published",
        summary: String = "summary",
    ) = AlbumWikiApiModel(
        published,
        summary
    )

    private fun getAlbumEntityModel(
        id: Int = 0,
        mbId: String = "mbId",
        name: String = "album",
        artist: String = "artist",
        images: List<AlbumImageEntityModel> = listOf(getAlbumImageEntityModel()),
        tracks: List<AlbumTrackEntityModel> = listOf(getAlbumTrackEntityModel()),
        tags: List<AlbumTagEntityModel> = listOf(getAlbumTagEntityModel()),
    ): AlbumEntityModel = AlbumEntityModel(
        id,
        mbId,
        name,
        artist,
        images,
        tracks,
        tags
    )

    private fun getAlbumImageEntityModel(
        url: String = "url_${AlbumImageSizeApiModel.EXTRA_LARGE}",
        size: AlbumImageSizeEntityModel = AlbumImageSizeEntityModel.EXTRA_LARGE,
    ) = AlbumImageEntityModel(
        url,
        size
    )

    private fun getAlbumTrackEntityModel(
        name: String = "track",
        duration: Int = 12,
    ) = AlbumTrackEntityModel(
        name,
        duration
    )

    private fun getAlbumTagEntityModel(
        name: String = "tag",
    ) = AlbumTagEntityModel(
        name
    )

    object ApiResponse {
        internal fun getSearchAlbum() = SearchAlbumResponse(
            AlbumSearchApiModel(
                AlbumListApiModel(getAlbumsApiModels())
            )
        )
    }
}
