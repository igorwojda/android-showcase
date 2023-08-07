package com.igorwojda.showcase.album.data.datasource.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchAlbumResultsApiModel(
    @SerialName("albummatches") val albumMatches: AlbumListApiModel,
)
