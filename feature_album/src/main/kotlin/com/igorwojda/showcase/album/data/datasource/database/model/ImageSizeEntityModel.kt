package com.igorwojda.showcase.album.data.datasource.database.model

import com.igorwojda.showcase.album.domain.enum.ImageSize

internal enum class ImageSizeEntityModel {
    MEDIUM, SMALL, LARGE, EXTRA_LARGE, MEGA
}

internal fun ImageSizeEntityModel.toDomainModel() =
    ImageSize.values().firstOrNull { it.ordinal == this.ordinal }
