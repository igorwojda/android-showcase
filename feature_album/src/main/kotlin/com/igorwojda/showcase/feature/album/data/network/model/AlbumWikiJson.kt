package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumWiki
import com.squareup.moshi.Json

internal data class AlbumWikiJson(
    @field:Json(name = "published") val published: String,
    @field:Json(name = "summary") val summary: String
)

internal fun AlbumWikiJson.toDomainModel() = AlbumWiki(
    published = this.published,
    summary = this.summary
)
