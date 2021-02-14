package com.igorwojda.showcase.feature.album.data.database.model

import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize

data class AlbumImageDataEntity(val url: String, val size: AlbumDataImageSizeEntity)

internal fun AlbumDataImageSizeEntity.toDomainEnum() =
    AlbumDomainImageSize.values().firstOrNull { it.ordinal == this.ordinal }
