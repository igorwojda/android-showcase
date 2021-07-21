package com.igorwojda.showcase.feature.album.data.network.enum

import com.igorwojda.showcase.feature.album.data.database.model.AlbumImageSizeEntity
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.squareup.moshi.Json

internal enum class AlbumImageSizeJson {

    @field:Json(name = "medium")
    MEDIUM,
    @field:Json(name = "small")
    SMALL,
    @field:Json(name = "large")
    LARGE,
    @field:Json(name = "extralarge")
    EXTRA_LARGE,
    @field:Json(name = "mega")
    MEGA,
    @field:Json(name = "")
    UNKNOWN
}

internal fun AlbumImageSizeJson.toDomainModel() = AlbumDomainImageSize.valueOf(this.name)

internal fun AlbumImageSizeJson.toEntityEnum() =
    AlbumImageSizeEntity.values().firstOrNull { it.ordinal == this.ordinal }
