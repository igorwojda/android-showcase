package com.igorwojda.showcase.album.data.datasource.api.model

import com.igorwojda.showcase.album.data.datasource.database.model.TagEntityModel
import com.igorwojda.showcase.album.domain.model.Tag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TagApiModel(
    @SerialName("name") val name: String,
)

internal fun TagApiModel.toDomainModel() = Tag(
    name = this.name,
)

internal fun TagApiModel.toEntityModel() = TagEntityModel(
    name = this.name,
)
