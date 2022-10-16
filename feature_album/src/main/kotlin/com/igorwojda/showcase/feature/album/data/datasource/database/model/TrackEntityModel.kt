package com.igorwojda.showcase.feature.album.data.datasource.database.model

import com.igorwojda.showcase.feature.album.domain.model.Track
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumTrackEntityModel(
    val name: String,
    val duration: Int? = null,
)

internal fun AlbumTrackEntityModel.toDomainModel() = Track(
    name = this.name,
    duration = this.duration
)
