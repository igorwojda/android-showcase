package com.igorwojda.showcase.feature.album.domain

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import com.igorwojda.showcase.feature.album.domain.model.AlbumTrack
import com.igorwojda.showcase.feature.album.domain.model.AlbumWiki

object DomainFixtures {

    internal fun getAlbum(
        name: String = "albumName",
        artist: String = "artistName",
        wiki: AlbumWiki? = getAlbumWikiDomainModel(),
        mbId: String? = "mbId",
        images: List<AlbumImage> = listOf(getAlbumImage()),
        tracks: List<AlbumTrack> = listOf(getAlbumTrack()),
    ): Album = Album(name, artist, wiki, mbId, images)

    internal fun getAlbumImage(
        url: String = "url_${AlbumDomainImageSize.EXTRA_LARGE}",
        size: AlbumDomainImageSize = AlbumDomainImageSize.EXTRA_LARGE,
    ) = AlbumImage(url, size)

    private fun getAlbumTrack(
        name: String = "track",
        duration: Int = 12,
    ) = AlbumTrack(
        name,
        duration
    )

    private fun getAlbumWikiDomainModel(
        published: String = "published",
        summary: String = "summary",
    ) = AlbumWiki(published, summary)
}
