package com.igorwojda.showcase.feature.album.data.datasource.database.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumTrack
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumTrackEntityModel(
    val name: String,
    val duration: Int,
)

internal fun AlbumTrackEntityModel.toDomainModel() = AlbumTrack(
    name = this.name,
    duration = this.duration
)
