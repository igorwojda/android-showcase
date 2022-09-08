package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.DataFixtures.ApiModel.getAlbums
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

object DataFixtures {

    object ApiModel {
        internal fun getAlbum(
            mbId: String = "mbId",
            name: String = "album",
            artist: String = "artist",
            wiki: AlbumWikiApiModel? = getAlbumWiki(),
            images: List<AlbumImageApiModel>? = listOf(getAlbumImage()),
        ): AlbumApiModel = AlbumApiModel(mbId, name, artist, wiki, images)

        internal fun getAlbums() = listOf(
            getAlbum(mbId = "mbid1", "album1", "artist1"),
            getAlbum(mbId = "mbid2", "album2", "artist2")
        )

        internal fun getAlbumImage(
            url: String = "url_${AlbumImageSizeApiModel.EXTRA_LARGE}",
            size: AlbumImageSizeApiModel = AlbumImageSizeApiModel.EXTRA_LARGE,
        ) = AlbumImageApiModel(url, size)

        internal fun getAlbumWiki(
            published: String = "published",
            summary: String = "summary",
        ) = AlbumWikiApiModel(published, summary)

        // TODO unused? why? not needed in tests?
        internal fun getAlbumEntity(
            id: Int = 0,
            mbId: String = "mbId",
            name: String = "album",
            artist: String = "artist",
            images: List<AlbumImageEntityModel> = listOf(getAlbumImageEntity()),
        ): AlbumEntityModel = AlbumEntityModel(id, mbId, name, artist, images)

        internal fun getAlbumImageEntity(
            url: String = "url_${AlbumImageSizeApiModel.EXTRA_LARGE}",
            size: AlbumImageSizeEntityModel = AlbumImageSizeEntityModel.EXTRA_LARGE,
        ) = AlbumImageEntityModel(url, size)
    }

    object ApiResponse {
        internal fun getSearchAlbum() = SearchAlbumResponse(
            AlbumSearchApiModel(
                AlbumListApiModel(getAlbums())
            )
        )
    }
}
