package com.igorwojda.showcase.feature.album.data.api.response

import com.igorwojda.showcase.feature.album.data.api.model.AlbumSearchJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchAlbumResponse(
    @SerialName("results") val results: AlbumSearchJson,
)
