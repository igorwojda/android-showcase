package com.igorwojda.showcase.album.data.datasource.database.model

import com.igorwojda.showcase.album.domain.model.Tag
import kotlinx.serialization.Serializable

@Serializable
internal data class TagEntityModel(
    val name: String,
)

internal fun TagEntityModel.toDomainModel() = Tag(
    name = this.name,
)
