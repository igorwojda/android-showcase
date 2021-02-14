package com.igorwojda.showcase.feature.album.data.database.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumImageDomainModel

enum class AlbumDataImageSizeEntity {
    MEDIUM, SMALL, LARGE, EXTRA_LARGE, MEGA;
}

internal fun AlbumImageDataEntity.toDomainModel() =
    this.size.toDomainEnum()?.let { AlbumImageDomainModel(this.url, it) }
