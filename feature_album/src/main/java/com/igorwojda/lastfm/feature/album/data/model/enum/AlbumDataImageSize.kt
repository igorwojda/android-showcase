package com.igorwojda.lastfm.feature.album.data.model.enum

import com.squareup.moshi.Json

enum class AlbumDataImageSize {
    @field:Json(name = "medium")
    MEDIUM,
    @field:Json(name = "small")
    SMALL,
    @field:Json(name = "large")
    LARGE,
    @field:Json(name = "extralarge")
    EXTRA_LARGE
}
