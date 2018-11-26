package com.igorwojda.lastfm.feature.album.domain.model.searchalbum

data class AlbumDomainModel(
    val name: String,
    val artist: String,
    val images: List<AlbumImageDomainModel>
)
