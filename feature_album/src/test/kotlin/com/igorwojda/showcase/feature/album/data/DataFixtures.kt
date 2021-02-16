package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.network.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.album.data.network.model.AlbumDataModel
import com.igorwojda.showcase.feature.album.data.network.model.AlbumImageDataModel
import com.igorwojda.showcase.feature.album.data.network.model.AlbumWikiDataModel

object DataFixtures {

    internal fun getAlbum(
        mbId: String = "mbId",
        name: String = "albumName",
        artist: String = "artistName",
        wiki: AlbumWikiDataModel? = getAlbumWikiDataModel(),
        images: List<AlbumImageDataModel>? = listOf(getAlbumImage())
    ): AlbumDataModel = AlbumDataModel(mbId, name, artist, wiki, images)

    internal fun getMinimalAlbum(): AlbumDataModel =
        getAlbum(
            name = "name",
            artist = "artist",
            mbId = "mbId",
            wiki = null,
            images = null
        )

    internal fun getAlbumImage(
        url: String = "url_${AlbumDataImageSize.EXTRA_LARGE}",
        size: AlbumDataImageSize = AlbumDataImageSize.EXTRA_LARGE
    ) = AlbumImageDataModel(url, size)

    internal fun getAlbumWikiDataModel(
        published: String = "published",
        summary: String = "summary"
    ) = AlbumWikiDataModel(published, summary)
}
