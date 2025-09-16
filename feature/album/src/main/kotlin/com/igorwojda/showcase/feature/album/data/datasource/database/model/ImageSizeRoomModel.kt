package com.igorwojda.showcase.feature.album.data.datasource.database.model

import com.igorwojda.showcase.feature.album.domain.enum.ImageSize

internal enum class ImageSizeRoomModel {
    MEDIUM,
    SMALL,
    LARGE,
    EXTRA_LARGE,
    MEGA,
}

internal fun ImageSizeRoomModel.toDomainModel() = ImageSize.values().firstOrNull { it.ordinal == this.ordinal }
