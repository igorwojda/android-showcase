package com.igorwojda.showcase.feature.album.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumSearchJson(
    @SerialName("albummatches") val albumMatches: AlbumListJson,
)
