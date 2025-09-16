package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.TagRoomModel
import com.igorwojda.showcase.feature.album.domain.model.Tag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TagApiModel(
    @SerialName("name") val name: String,
)

internal fun TagApiModel.toDomainModel() =
    Tag(
        name = this.name,
    )

internal fun TagApiModel.toRoomModel() =
    TagRoomModel(
        name = this.name,
    )
