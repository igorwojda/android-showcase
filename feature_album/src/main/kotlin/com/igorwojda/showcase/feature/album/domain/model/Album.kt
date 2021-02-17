package com.igorwojda.showcase.feature.album.domain.model

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize

internal data class Album(
    val name: String,
    val artist: String,
    val images: List<AlbumImage>,
    val wiki: AlbumWiki? = null,
    val mbId: String? = null
) {
    fun getDefaultImageUrl() = images.firstOrNull { it.size == AlbumDomainImageSize.EXTRA_LARGE }?.url
}
