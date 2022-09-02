package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.domain.model.AlbumWiki
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AlbumWikiApi(
    @SerialName("published") val published: String,
    @SerialName("summary") val summary: String,
)

internal fun AlbumWikiApi.toDomainModel() = AlbumWiki(
    published = this.published,
    summary = this.summary
)
