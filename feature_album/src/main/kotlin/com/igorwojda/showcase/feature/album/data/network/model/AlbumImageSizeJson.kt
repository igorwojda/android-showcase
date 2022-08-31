package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.data.database.model.AlbumImageSizeEntity
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class AlbumImageSizeJson {

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

internal fun AlbumImageSizeJson.toDomainModel() = AlbumDomainImageSize.valueOf(this.name)

internal fun AlbumImageSizeJson.toEntityEnum() =
    AlbumImageSizeEntity.values().firstOrNull { it.ordinal == this.ordinal }
