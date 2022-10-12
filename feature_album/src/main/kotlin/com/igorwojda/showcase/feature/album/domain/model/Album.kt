package com.igorwojda.showcase.feature.album.domain.model

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize

// Images are loaded for both album list and album detail instance
// Tracks and Tags are only loaded for album detail instance (not album list instance)
internal data class Album(
    val name: String,
    val artist: String,
    val wiki: AlbumWiki? = null,
    val mbId: String? = null,
    val images: List<AlbumImage> = emptyList(),
    val tracks: List<AlbumTrack>? = null,
    val tags: List<AlbumTag>? = null,
) {
    fun getDefaultImageUrl() = images.firstOrNull { it.size == AlbumDomainImageSize.EXTRA_LARGE }?.url
}
