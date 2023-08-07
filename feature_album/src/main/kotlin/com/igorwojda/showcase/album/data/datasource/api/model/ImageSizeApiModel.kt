package com.igorwojda.showcase.album.data.datasource.api.model

import com.igorwojda.showcase.album.data.datasource.database.model.ImageSizeEntityModel
import com.igorwojda.showcase.album.domain.enum.ImageSize
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

internal fun ImageSizeApiModel.toEntityModel() =
    ImageSizeEntityModel.values().firstOrNull { it.ordinal == this.ordinal }
