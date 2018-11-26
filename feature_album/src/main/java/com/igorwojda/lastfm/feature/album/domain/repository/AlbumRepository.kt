package com.igorwojda.lastfm.feature.album.domain.repository

import com.igorwojda.lastfm.feature.album.domain.model.searchalbum.AlbumDomainModel

interface AlbumRepository {
    suspend fun getAlbum(id: String, artistName: String): AlbumDomainModel?

    suspend fun searchAlbum(phrase: String): List<AlbumDomainModel>
}
