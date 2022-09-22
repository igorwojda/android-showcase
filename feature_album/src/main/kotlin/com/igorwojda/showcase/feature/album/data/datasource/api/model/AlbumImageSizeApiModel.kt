package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageSizeEntityModel
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class AlbumImageSizeApiModel {

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
    UNKNOWN
}

internal fun AlbumImageSizeApiModel.toDomainModel() = AlbumDomainImageSize.valueOf(this.name)

internal fun AlbumImageSizeApiModel.toEntityModel() =
    AlbumImageSizeEntityModel.values().firstOrNull { it.ordinal == this.ordinal }
