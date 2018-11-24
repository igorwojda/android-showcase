package com.igorwojda.lastfm.feature.album.domain.repository

import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel

interface AlbumRepository {
    suspend fun getAlbumList(): List<AlbumDomainModel>

    suspend fun getAlbum(id: Int): AlbumDomainModel?
}
