package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumWiki
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumWikiJson(
    @SerialName("published") val published: String,
    @SerialName("summary") val summary: String,
)

internal fun AlbumWikiJson.toDomainModel() = AlbumWiki(
    published = this.published,
    summary = this.summary
)
