package com.igorwojda.lastfm.feature.album.domain.repository

import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel

interface AlbumRepository {
    suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String): AlbumDomainModel?

    suspend fun searchAlbum(phrase: String): List<AlbumDomainModel>
}
