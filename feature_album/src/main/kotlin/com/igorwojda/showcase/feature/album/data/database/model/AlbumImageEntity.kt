package com.igorwojda.showcase.feature.album.data.database.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumImage

data class AlbumImageEntity(val url: String, val size: AlbumImageSizeEntity)

internal fun AlbumImageEntity.toDomainModel() =
    this.size.toDomainEnum()?.let { AlbumImage(this.url, it) }
