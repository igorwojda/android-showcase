package com.igorwojda.showcase.feature.album.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumListJson(
    @SerialName("album") val album: List<AlbumJson>,
)
