package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageRoomModel
import com.igorwojda.showcase.feature.album.domain.model.Image

internal class ImageMapper(
    private val imageSizeMapper: ImageSizeMapper,
) {
    fun apiToDomain(apiModel: ImageApiModel) =
        Image(
            url = apiModel.url,
            size = imageSizeMapper.apiToDomain(apiModel.size),
        )

    fun apiToRoom(apiModel: ImageApiModel) =
        imageSizeMapper.apiToRoom(apiModel.size)?.let {
            ImageRoomModel(apiModel.url, it)
        }

    fun roomToDomain(roomModel: ImageRoomModel) =
        imageSizeMapper.roomToDomain(roomModel.size)?.let {
            Image(roomModel.url, it)
        }
}
