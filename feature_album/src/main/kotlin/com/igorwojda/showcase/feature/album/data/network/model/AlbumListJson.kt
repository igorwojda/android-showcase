package com.igorwojda.showcase.feature.album.data.network.model

import com.squareup.moshi.Json

internal data class AlbumListJson(
    @field:Json(name = "album") val album: List<AlbumJson>
)
