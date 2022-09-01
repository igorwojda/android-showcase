package com.igorwojda.showcase.feature.album.data.datasource.api.response

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAlbumInfoResponse(
    @SerialName("album") val album: AlbumJson,
)
