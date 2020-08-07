package com.igorwojda.showcase.feature.album.domain

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumImageDomainModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumWikiDomainModel

object DomainFixtures {

    internal fun getAlbum(
        name: String = "albumName",
        artist: String = "artistName",
        images: List<AlbumImageDomainModel> = listOf(getAlbumImage()),
        wiki: AlbumWikiDomainModel? = getAlbumWikiDomainModel(),
        mbId: String? = "mbId"
    ): AlbumDomainModel = AlbumDomainModel(name, artist, images, wiki, mbId)

    internal fun getAlbumImage(
        url: String = "url_${AlbumDomainImageSize.EXTRA_LARGE}",
        size: AlbumDomainImageSize = AlbumDomainImageSize.EXTRA_LARGE
    ) = AlbumImageDomainModel(url, size)

    private fun getAlbumWikiDomainModel(
        published: String = "published",
        summary: String = "summary"
    ) = AlbumWikiDomainModel(published, summary)
}
