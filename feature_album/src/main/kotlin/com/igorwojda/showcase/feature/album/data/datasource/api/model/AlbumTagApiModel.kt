package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumTagEntityModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumTag
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumTagApiModel(
    @SerialName("name") val name: String,
)

internal fun AlbumTagApiModel.toDomainModel() = AlbumTag(
    name = this.name
)

internal fun AlbumTagApiModel.toEntityModel() = AlbumTagEntityModel(
    name = this.name
)
