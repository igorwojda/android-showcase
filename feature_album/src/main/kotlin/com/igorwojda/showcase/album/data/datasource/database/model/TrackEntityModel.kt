package com.igorwojda.showcase.album.data.datasource.database.model

import com.igorwojda.showcase.album.domain.model.Track
import kotlinx.serialization.Serializable

@Serializable
internal data class TrackEntityModel(
    val name: String,
    val duration: Int? = null,
)

internal fun TrackEntityModel.toDomainModel() = Track(
    name = this.name,
    duration = this.duration,
)
