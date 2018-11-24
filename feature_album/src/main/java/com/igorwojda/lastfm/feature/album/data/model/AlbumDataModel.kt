package com.igorwojda.lastfm.feature.album.data.model

import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel

data class AlbumNetworkModel(
    val id: Int,
    val userId: Int,
    val title: String?
)

fun AlbumNetworkModel.toDomainModel() = AlbumDomainModel(
    id = this.id,
    userId = this.userId,
    title = this.title ?: ""
)
