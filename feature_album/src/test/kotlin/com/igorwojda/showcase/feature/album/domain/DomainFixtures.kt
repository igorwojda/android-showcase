package com.igorwojda.showcase.feature.album.domain

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import com.igorwojda.showcase.feature.album.domain.model.AlbumTag
import com.igorwojda.showcase.feature.album.domain.model.AlbumTrack

object DomainFixtures {

    internal fun getAlbum(
        name: String = "albumName",
        artist: String = "artistName",
        mbId: String? = "mbId",
        images: List<AlbumImage> = listOf(getAlbumImage()),
        tracks: List<AlbumTrack> = listOf(getAlbumTrack()),
        tags: List<AlbumTag> = listOf(getAlbumTag()),
    ): Album = Album(name, artist, mbId, images, tracks, tags)

    internal fun getAlbumImage(
        url: String = "url_${AlbumDomainImageSize.EXTRA_LARGE}",
        size: AlbumDomainImageSize = AlbumDomainImageSize.EXTRA_LARGE,
    ) = AlbumImage(url, size)

    private fun getAlbumTrack(
        name: String = "track",
        duration: Int = 12,
    ) = AlbumTrack(name, duration)

    private fun getAlbumTag(
        name: String = "tag",
    ) = AlbumTag(name)
}
