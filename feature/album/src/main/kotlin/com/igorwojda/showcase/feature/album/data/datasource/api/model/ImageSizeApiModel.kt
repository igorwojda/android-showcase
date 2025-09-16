package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.ImageSizeRoomModel
import com.igorwojda.showcase.feature.album.domain.enum.ImageSize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class ImageSizeApiModel {
    @SerialName("medium")
    MEDIUM,

    @SerialName("small")
    SMALL,

    @SerialName("large")
    LARGE,

    @SerialName("extralarge")
    EXTRA_LARGE,

    @SerialName("mega")
    MEGA,

    @SerialName("")
    UNKNOWN,
}

internal fun ImageSizeApiModel.toDomainModel() = ImageSize.valueOf(this.name)

internal fun ImageSizeApiModel.toRoomModel(): ImageSizeRoomModel? =
    when (this) {
        ImageSizeApiModel.MEDIUM -> ImageSizeRoomModel.MEDIUM
        ImageSizeApiModel.SMALL -> ImageSizeRoomModel.SMALL
        ImageSizeApiModel.LARGE -> ImageSizeRoomModel.LARGE
        ImageSizeApiModel.EXTRA_LARGE -> ImageSizeRoomModel.EXTRA_LARGE
        ImageSizeApiModel.MEGA -> ImageSizeRoomModel.MEGA
        ImageSizeApiModel.UNKNOWN -> null
    }
