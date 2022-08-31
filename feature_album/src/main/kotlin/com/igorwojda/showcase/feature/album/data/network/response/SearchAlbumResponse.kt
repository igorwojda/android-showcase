package com.igorwojda.showcase.feature.album.data.network.response

import com.igorwojda.showcase.feature.album.data.network.model.AlbumSearchJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchAlbumResponse(
    @SerialName("results") val results: AlbumSearchJson,
)
