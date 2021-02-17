package com.igorwojda.showcase.feature.album.data.database.model

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize

enum class AlbumImageSizeEntity {
    MEDIUM, SMALL, LARGE, EXTRA_LARGE, MEGA;
}

internal fun AlbumImageSizeEntity.toDomainEnum() =
    AlbumDomainImageSize.values().firstOrNull { it.ordinal == this.ordinal }
