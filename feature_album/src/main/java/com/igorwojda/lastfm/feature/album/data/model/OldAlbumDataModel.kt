package com.igorwojda.lastfm.feature.album.data.model

import com.igorwojda.lastfm.feature.album.domain.model.OldAlbumDomainModel

data class AlbumNetworkModel(
    val id: Int,
    val userId: Int,
    val title: String?
)

fun AlbumNetworkModel.toDomainModel() = OldAlbumDomainModel(
    id = this.id,
    userId = this.userId,
    title = this.title ?: ""
)
