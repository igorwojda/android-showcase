package com.igorwojda.showcase.feature.album.data.datasource.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumSearchJson(
    @SerialName("albummatches") val albumMatches: AlbumListJson,
)
