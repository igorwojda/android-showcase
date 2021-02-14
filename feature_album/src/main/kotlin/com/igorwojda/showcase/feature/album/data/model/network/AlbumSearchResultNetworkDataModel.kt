package com.igorwojda.showcase.feature.album.data.model.network

import com.squareup.moshi.Json

internal data class AlbumSearchResultNetworkDataModel(
    @field:Json(name = "albummatches") val albumMatchesNetwork: AlbumListNetworkDataModel
)
