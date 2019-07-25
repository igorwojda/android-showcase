package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumWikiDomainModel

internal data class AlbumWikiDataModel(
    val published: String,
    val summary: String
)

internal fun AlbumWikiDataModel.toDomainModel() = AlbumWikiDomainModel(
    published = this.published,
    summary = this.summary
)
