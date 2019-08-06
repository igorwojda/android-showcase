package com.igorwojda.showcase.feature.album.domain

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumImageDomainModel

object DomainFixtures {
    internal fun getAlbumList() = listOf(getAlbum(listOf(AlbumDomainImageSize.EXTRA_LARGE)))

    internal fun getAlbum(imageSizes: List<AlbumDomainImageSize>?): AlbumDomainModel {
        val images = imageSizes?.map { getAlbumImage(it) } ?: listOf()
        return AlbumDomainModel("name", "artist", images, null, null)
    }

    internal fun getAlbumImage(size: AlbumDomainImageSize = AlbumDomainImageSize.EXTRA_LARGE) =
        AlbumImageDomainModel("url_${AlbumDomainImageSize.EXTRA_LARGE}", size)

    internal fun getAlbum(imageSize: AlbumImageDomainModel): AlbumDomainModel {
        return AlbumDomainModel("name", "artist", listOf(imageSize), null, null)
    }
}
