package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumImage

enum class AlbumDataImageSizeEntity {
    MEDIUM, SMALL, LARGE, EXTRA_LARGE, MEGA;
}

internal fun AlbumImageDataEntity.toDomainModel() =
    this.size.toDomainEnum()?.let { AlbumImage(this.url, it) }
