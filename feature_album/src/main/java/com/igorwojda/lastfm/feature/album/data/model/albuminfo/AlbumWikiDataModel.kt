package com.igorwojda.lastfm.feature.album.data.model.albuminfo

import com.igorwojda.lastfm.feature.album.domain.model.AlbumWikiDomainModel

data class AlbumWikiDataModel(
    val published: String,
    val summary: String
)

fun AlbumWikiDataModel.toDomainModel() = AlbumWikiDomainModel(
    published = this.published,
    summary = this.summary
)
