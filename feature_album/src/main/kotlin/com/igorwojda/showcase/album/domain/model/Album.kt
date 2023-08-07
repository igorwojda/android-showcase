package com.igorwojda.showcase.album.domain.model

import com.igorwojda.showcase.album.domain.enum.ImageSize

// Images are loaded for both album list and album detail instance
// Tracks and Tags are only loaded for album detail instance (not album list instance)
internal data class Album(
    val name: String,
    val artist: String,
    val mbId: String? = null,
    val images: List<Image> = emptyList(),
    val tracks: List<Track>? = null,
    val tags: List<Tag>? = null,
) {

    val id: String = "$artist - $name"

    fun getDefaultImageUrl() = images.firstOrNull { it.size == ImageSize.EXTRA_LARGE }?.url
}
