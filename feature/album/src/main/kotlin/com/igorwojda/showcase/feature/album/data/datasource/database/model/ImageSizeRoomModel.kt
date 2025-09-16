package com.igorwojda.showcase.feature.album.data.datasource.database.model

import com.igorwojda.showcase.feature.album.domain.enum.ImageSize

internal enum class ImageSizeRoomModel {
    MEDIUM,
    SMALL,
    LARGE,
    EXTRA_LARGE,
    MEGA,
}

internal fun ImageSizeRoomModel.toDomainModel(): ImageSize? =
    when (this) {
        ImageSizeRoomModel.MEDIUM -> ImageSize.MEDIUM
        ImageSizeRoomModel.SMALL -> ImageSize.SMALL
        ImageSizeRoomModel.LARGE -> ImageSize.LARGE
        ImageSizeRoomModel.EXTRA_LARGE -> ImageSize.EXTRA_LARGE
        ImageSizeRoomModel.MEGA -> ImageSize.MEGA
    }
