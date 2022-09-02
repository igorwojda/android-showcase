package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumImageApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumImageSizeApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumListApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumSearchApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumWikiApiModel
import com.igorwojda.showcase.feature.album.data.datasource.api.response.SearchAlbumResponse
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumEntity
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageEntity
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageSizeEntityModel

object DataFixtures {

    internal fun getAlbumApiModel(
        mbId: String = "mbId",
        name: String = "albumName",
        artist: String = "artistName",
        wiki: AlbumWikiApiModel? = getAlbumWikiApiModel(),
        images: List<AlbumImageApiModel>? = listOf(getAlbumImageApiModel()),
    ): AlbumApiModel = AlbumApiModel(mbId, name, artist, wiki, images)

    internal fun getAlbumsApiModel() = listOf(
        getAlbumApiModel(mbId = "mbid1", "albumName1", "artist1"),
        getAlbumApiModel(mbId = "mbid2", "albumName2", "artist2")
    )

    internal fun getMinimalAlbumApi(): AlbumApiModel =
        getAlbumApiModel(
            name = "name",
            artist = "artist",
            mbId = "mbId",
            wiki = null,
            images = null
        )

    internal fun getAlbumImageApiModel(
        url: String = "url_${AlbumImageSizeApiModel.EXTRA_LARGE}",
        size: AlbumImageSizeApiModel = AlbumImageSizeApiModel.EXTRA_LARGE,
    ) = AlbumImageApiModel(url, size)

    internal fun getAlbumWikiApiModel(
        published: String = "published",
        summary: String = "summary",
    ) = AlbumWikiApiModel(published, summary)

    // TODO unused?
    internal fun getAlbumEntity(
        id: Int = 0,
        mbId: String = "mbId",
        name: String = "albumName",
        artist: String = "artistName",
        images: List<AlbumImageEntity> = listOf(getAlbumImageEntity()),
    ): AlbumEntity = AlbumEntity(id, mbId, name, artist, images)

    internal fun getAlbumImageEntity(
        url: String = "url_${AlbumImageSizeApiModel.EXTRA_LARGE}",
        size: AlbumImageSizeEntityModel = AlbumImageSizeEntityModel.EXTRA_LARGE,
    ) = AlbumImageEntity(url, size)

    internal fun getSearchAlbumResponse() = SearchAlbumResponse(
        AlbumSearchApiModel(
            AlbumListApiModel(getAlbumsApiModel())
        )
    )
}
