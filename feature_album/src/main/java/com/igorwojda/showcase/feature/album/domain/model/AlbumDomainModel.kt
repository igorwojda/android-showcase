package com.igorwojda.showcase.feature.album.domain.model

internal data class AlbumDomainModel(
    val name: String,
    val artist: String,
    val images: List<AlbumImageDomainModel>,
    val wiki: AlbumWikiDomainModel?,
    val mbId: String?
)
