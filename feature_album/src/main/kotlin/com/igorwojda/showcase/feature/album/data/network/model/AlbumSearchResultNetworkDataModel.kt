package com.igorwojda.showcase.feature.album.data.network.model

import com.squareup.moshi.Json

internal data class AlbumSearchResultNetworkDataModel(
    @field:Json(name = "albummatches") val albumMatchesNetwork: AlbumListNetworkDataModel
)
