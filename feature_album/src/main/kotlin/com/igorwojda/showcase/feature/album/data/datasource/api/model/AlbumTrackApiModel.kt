package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumTrackEntityModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumTrack
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumTrackApiModel(
    @SerialName("name") val name: String,
    @SerialName("duration") val duration: Int? = null,
)

internal fun AlbumTrackApiModel.toDomainModel() = AlbumTrack(
    name = this.name,
    duration = this.duration
)

internal fun AlbumTrackApiModel.toEntityModel() = AlbumTrackEntityModel(
    name = this.name,
    duration = this.duration
)
