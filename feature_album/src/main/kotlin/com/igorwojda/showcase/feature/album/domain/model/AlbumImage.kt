package com.igorwojda.showcase.feature.album.domain.model

import com.igorwojda.showcase.feature.album.domain.enum.ImageSize

internal data class AlbumImage(
    val url: String,
    val size: ImageSize,
)
