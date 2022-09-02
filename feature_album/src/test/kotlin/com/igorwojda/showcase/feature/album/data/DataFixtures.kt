package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumImageJson
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumImageSizeApi
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumJson
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumWikiJson
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumEntity
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageEntity
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageSizeEntity

object DataFixtures {

    internal fun getAlbum(
        mbId: String = "mbId",
        name: String = "albumName",
        artist: String = "artistName",
        wiki: AlbumWikiJson? = getAlbumWikiDataModel(),
        images: List<AlbumImageJson>? = listOf(getAlbumImage()),
    ): AlbumJson = AlbumJson(mbId, name, artist, wiki, images)

    internal fun getAlbums() = listOf(
        getAlbum(mbId = "mbid1", "albumName1", "artist1"),
        getAlbum(mbId = "mbid2", "albumName2", "artist2")
    )

    internal fun getMinimalAlbum(): AlbumJson =
        getAlbum(
            name = "name",
            artist = "artist",
            mbId = "mbId",
            wiki = null,
            images = null
        )

    internal fun getAlbumImage(
        url: String = "url_${AlbumImageSizeApi.EXTRA_LARGE}",
        size: AlbumImageSizeApi = AlbumImageSizeApi.EXTRA_LARGE,
    ) = AlbumImageJson(url, size)

    internal fun getAlbumWikiDataModel(
        published: String = "published",
        summary: String = "summary"
    ) = AlbumWikiJson(published, summary)

    internal fun getAlbumEntity(
        id: Int = 0,
        mbId: String = "mbId",
        name: String = "albumName",
        artist: String = "artistName",
        images: List<AlbumImageEntity> = listOf(getAlbumImageEntity())
    ): AlbumEntity = AlbumEntity(id, mbId, name, artist, images)

    internal fun getAlbumImageEntity(
        url: String = "url_${AlbumImageSizeApi.EXTRA_LARGE}",
        size: AlbumImageSizeEntity = AlbumImageSizeEntity.EXTRA_LARGE,
    ) = AlbumImageEntity(url, size)
}
