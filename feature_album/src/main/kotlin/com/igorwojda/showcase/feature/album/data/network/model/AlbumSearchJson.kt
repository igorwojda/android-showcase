package com.igorwojda.showcase.feature.album.data.network.model

import com.squareup.moshi.Json

internal data class AlbumSearchJson(
    @field:Json(name = "albummatches") val albumMatchesJson: AlbumListJson
)
