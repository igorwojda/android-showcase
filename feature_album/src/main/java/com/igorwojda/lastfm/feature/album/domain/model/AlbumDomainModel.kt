package com.igorwojda.lastfm.feature.album.domain.model

data class AlbumDomainModel(
    val name: String,
    val artist: String,
    val images: List<AlbumImageDomainModel>,
    val wiki: AlbumWikiDomainModel?,
    val mbId: String
)
