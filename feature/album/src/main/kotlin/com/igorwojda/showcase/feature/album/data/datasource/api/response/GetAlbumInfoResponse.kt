package com.igorwojda.showcase.feature.album.data.datasource.api.response

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GetAlbumInfoResponse(
    @SerialName("album") val album: AlbumApiModel,
)
