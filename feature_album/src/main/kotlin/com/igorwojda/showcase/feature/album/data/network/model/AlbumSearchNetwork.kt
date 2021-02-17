package com.igorwojda.showcase.feature.album.data.network.model

import com.squareup.moshi.Json

internal data class AlbumSearchNetwork(
    @field:Json(name = "albummatches") val albumMatchesNetwork: AlbumListNetwork
)
