package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumWiki

internal data class AlbumWikiJson(
    val published: String,
    val summary: String
)

internal fun AlbumWikiJson.toDomainModel() = AlbumWiki(
    published = this.published,
    summary = this.summary
)
