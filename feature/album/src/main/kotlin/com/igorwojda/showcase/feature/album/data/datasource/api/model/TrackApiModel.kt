package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.TrackRoomModel
import com.igorwojda.showcase.feature.album.domain.model.Track
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TrackApiModel(
    @SerialName("name") val name: String,
    @SerialName("duration") val duration: Int? = null,
)

internal fun TrackApiModel.toDomainModel() =
    Track(
        name = this.name,
        duration = this.duration,
    )

internal fun TrackApiModel.toRoomModel() =
    TrackRoomModel(
        name = this.name,
        duration = this.duration,
    )
