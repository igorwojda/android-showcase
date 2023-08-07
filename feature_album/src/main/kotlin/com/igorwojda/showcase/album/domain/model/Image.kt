package com.igorwojda.showcase.album.domain.model

import com.igorwojda.showcase.album.domain.enum.ImageSize

internal data class Image(
    val url: String,
    val size: ImageSize,
)
