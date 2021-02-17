package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.database.model.AlbumEntity
import com.igorwojda.showcase.feature.album.data.database.model.AlbumImageEntity
import com.igorwojda.showcase.feature.album.data.database.model.AlbumImageSizeEntity
import com.igorwojda.showcase.feature.album.data.network.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.album.data.network.model.AlbumNetwork
import com.igorwojda.showcase.feature.album.data.network.model.AlbumImageNetwork
import com.igorwojda.showcase.feature.album.data.network.model.AlbumWikiDataModel

object DataFixtures {

    internal fun getAlbum(
        mbId: String = "mbId",
        name: String = "albumName",
        artist: String = "artistName",
        wiki: AlbumWikiDataModel? = getAlbumWikiDataModel(),
        images: List<AlbumImageNetwork>? = listOf(getAlbumImage())
    ): AlbumNetwork = AlbumNetwork(mbId, name, artist, wiki, images)

    internal fun getMinimalAlbum(): AlbumNetwork =
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
    ) = AlbumImageNetwork(url, size)

    internal fun getAlbumWikiDataModel(
        published: String = "published",
        summary: String = "summary"
    ) = AlbumWikiDataModel(published, summary)

    internal fun getAlbumEntity(
        id: Int = 0,
        mbId: String = "mbId",
        name: String = "albumName",
        artist: String = "artistName",
        images: List<AlbumImageEntity> = listOf(getAlbumImageEntity())
    ): AlbumEntity = AlbumEntity(id, mbId, name, artist, images)

    internal fun getAlbumImageEntity(
        url: String = "url_${AlbumDataImageSize.EXTRA_LARGE}",
        size: AlbumImageSizeEntity = AlbumImageSizeEntity.EXTRA_LARGE
    ) = AlbumImageEntity(url, size)
}
