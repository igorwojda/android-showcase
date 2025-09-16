package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.TagApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.TagRoomModel
import com.igorwojda.showcase.feature.album.domain.model.Tag

internal class TagMapper {
    fun apiToDomain(apiModel: TagApiModel): Tag =
        Tag(
            name = apiModel.name,
        )

    fun apiToRoom(apiModel: TagApiModel): TagRoomModel =
        TagRoomModel(
            name = apiModel.name,
        )

    fun roomToDomain(roomModel: TagRoomModel): Tag =
        Tag(
            name = roomModel.name,
        )
}
