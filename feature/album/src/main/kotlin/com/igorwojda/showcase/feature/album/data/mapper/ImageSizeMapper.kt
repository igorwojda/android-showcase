package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.ImageSizeApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageSizeRoomModel
import com.igorwojda.showcase.feature.album.domain.enum.ImageSize

internal class ImageSizeMapper {
    fun apiToDomain(apiModel: ImageSizeApiModel): ImageSize = ImageSize.valueOf(apiModel.name)

    fun apiToRoom(apiModel: ImageSizeApiModel): ImageSizeRoomModel? =
        when (apiModel) {
            ImageSizeApiModel.MEDIUM -> ImageSizeRoomModel.MEDIUM
            ImageSizeApiModel.SMALL -> ImageSizeRoomModel.SMALL
            ImageSizeApiModel.LARGE -> ImageSizeRoomModel.LARGE
            ImageSizeApiModel.EXTRA_LARGE -> ImageSizeRoomModel.EXTRA_LARGE
            ImageSizeApiModel.MEGA -> ImageSizeRoomModel.MEGA
            ImageSizeApiModel.UNKNOWN -> null
        }

    fun roomToDomain(roomModel: ImageSizeRoomModel): ImageSize? =
        when (roomModel) {
            ImageSizeRoomModel.MEDIUM -> ImageSize.MEDIUM
            ImageSizeRoomModel.SMALL -> ImageSize.SMALL
            ImageSizeRoomModel.LARGE -> ImageSize.LARGE
            ImageSizeRoomModel.EXTRA_LARGE -> ImageSize.EXTRA_LARGE
            ImageSizeRoomModel.MEGA -> ImageSize.MEGA
        }
}
