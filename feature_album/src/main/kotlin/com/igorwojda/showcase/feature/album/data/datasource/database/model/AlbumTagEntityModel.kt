package com.igorwojda.showcase.feature.album.data.datasource.database.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumTag
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumTagEntityModel(
    val name: String,
)

internal fun AlbumTagEntityModel.toDomainModel() = AlbumTag(
    name = this.name
)
