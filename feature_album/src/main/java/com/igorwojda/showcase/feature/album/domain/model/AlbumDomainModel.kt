package com.igorwojda.showcase.feature.album.domain.model

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize

internal data class AlbumDomainModel(
    val name: String,
    val artist: String,
    val images: List<AlbumImageDomainModel>,
    val wiki: AlbumWikiDomainModel?,
    val mbId: String?
) {
    fun getDefaultImageUrl() = images.firstOrNull { it.size == AlbumDomainImageSize.EXTRA_LARGE }?.url
}
