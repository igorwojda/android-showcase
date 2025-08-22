package com.igorwojda.showcase.feature.album.data.datasource.api.response

import com.igorwojda.showcase.feature.album.data.datasource.api.model.SearchAlbumResultsApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SearchAlbumResponse(
    @SerialName("results") val results: SearchAlbumResultsApiModel,
)
